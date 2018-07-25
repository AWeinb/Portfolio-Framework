package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise.FutureCallback;
import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface.EventListener;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface.EventListener.AnswerCallback;

class ListenerNotifier implements MainLoop.MainLoopListener {

	private final MainLoop.MainLoopAccessor outputBufferAccessor;
	private final Map<String, Map<String, EventListener>> listeners = Collections.synchronizedMap(new HashMap<>());

	ListenerNotifier(MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.outputBufferAccessor = outputBufferAccessor;
	}

	void addListener(String sessionId, String context, EventListener listener) {
		if (!listeners.containsKey(sessionId)) {
			listeners.put(sessionId, Collections.synchronizedMap(new HashMap<>()));
		}
		listeners.get(sessionId).put(context, listener);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		if (aPackage.getState() == MainLoopPackage.STATE.Poisoned) {
			return;
		}

		Event event = (Event) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String context = aPackage.getContext();
		String id = event.getId();

		FutureCallback succeed = future -> makePackage(sessionId, context, id, future, MainLoopPackage.STATE.Resolved);
		FutureCallback fail = future -> makePackage(sessionId, context, id, future, MainLoopPackage.STATE.Rejected);
		AnswerCallback answerCallback = new AnswerCallbackImpl(succeed, fail);

		Map<String, EventListener> listenerMap = listeners.get(aPackage.getSessionId());
		EventListener listener = listenerMap.get(aPackage.getContext());
		listener.handle(event, answerCallback);
	}

	private void makePackage(String sessionId, String context, String id, Object future,
	                         MainLoopPackage.STATE resolved) {
		Notification response = Notification.build(id, future);
		MainLoopPackage packedResponse = new MainLoopPackage(sessionId, context, response, resolved);
		outputBufferAccessor.put(packedResponse);
	}

	class AnswerCallbackImpl implements AnswerCallback {

		private final FutureCallback successCallback;
		private final FutureCallback failureCallback;

		AnswerCallbackImpl(FutureCallback successCallback, FutureCallback failureCallback) {
			this.successCallback = successCallback;
			this.failureCallback = failureCallback;
		}

		@Override
		public void triggerSuccess(Object result) {
			successCallback.execute(result);
		}

		@Override
		public void triggerFailure(Object result) {
			failureCallback.execute(result);
		}
	}
}
