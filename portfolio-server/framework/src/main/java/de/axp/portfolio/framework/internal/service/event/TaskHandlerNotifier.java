package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise.FutureCallback;
import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface.TaskHandler;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static de.axp.portfolio.framework.api.interfaces.TaskServiceInterface.TaskHandler.ResultCallback;

class TaskHandlerNotifier implements MainLoop.MainLoopListener {

	private final MainLoop.MainLoopAccessor outputBufferAccessor;
	private final Map<String, Map<String, TaskHandler>> handlers = Collections.synchronizedMap(new HashMap<>());

	TaskHandlerNotifier(MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.outputBufferAccessor = outputBufferAccessor;
	}

	void addListener(String sessionId, String context, TaskHandler handler) {
		if (!handlers.containsKey(sessionId)) {
			handlers.put(sessionId, Collections.synchronizedMap(new HashMap<>()));
		}
		handlers.get(sessionId).put(context, handler);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		if (aPackage.getState() == MainLoopPackage.STATE.Poisoned) {
			return;
		}

		Task task = (Task) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String context = aPackage.getContext();
		String id = task.getId();

		FutureCallback succeed = future -> makePackage(sessionId, context, id, future, MainLoopPackage.STATE.Resolved);
		FutureCallback fail = future -> makePackage(sessionId, context, id, future, MainLoopPackage.STATE.Rejected);
		ResultCallback answerCallback = new ResultCallbackImpl(succeed, fail);

		Map<String, TaskHandler> handlerMap = handlers.get(aPackage.getSessionId());
		TaskHandler handler = handlerMap.get(aPackage.getContext());
		handler.handle(task, answerCallback);
	}

	private void makePackage(String sessionId, String context, String id, Object future,
	                         MainLoopPackage.STATE resolved) {
		Notification response = Notification.build(id, future);
		MainLoopPackage packedResponse = new MainLoopPackage(sessionId, context, response, resolved);
		outputBufferAccessor.put(packedResponse);
	}

	class ResultCallbackImpl implements ResultCallback {

		private final FutureCallback successCallback;
		private final FutureCallback failureCallback;

		ResultCallbackImpl(FutureCallback successCallback, FutureCallback failureCallback) {
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
