package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static de.axp.portfolio.framework.internal.service.event.EventService.Event;

class EventServiceInputListener implements MainLoop.MainLoopListener {

	private final MainLoop.MainLoopAccessor outputBufferAccessor;
	private final Map<String, Map<String, EventService.EventConsumer>> consumers = Collections.synchronizedMap(
			new HashMap<>());

	EventServiceInputListener(MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.outputBufferAccessor = outputBufferAccessor;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		if (aPackage.getState() == MainLoopPackage.STATE.Poisoned) {
			return;
		}

		Event inputEvent = (Event) aPackage.getPayload();
		String sessionId = inputEvent.getSessionId();
		String context = inputEvent.getContext();
		String packageId = inputEvent.getPackageId();
		Object content = inputEvent.getData();
		FrameworkPromise promiseToResolveOrReject = createPromise(inputEvent);

		Event consumerEvent = Event.build(sessionId, context, packageId, content, promiseToResolveOrReject);
		consumers.get(sessionId).get(context).consume(consumerEvent);
	}

	private FrameworkPromise createPromise(Event event) {
		return FrameworkPromise.whenResolved(future -> {
			Event response = Event.buildOneWay(event.getSessionId(), event.getContext(), event.getPackageId(),
					event.getData());
			MainLoopPackage aPackage = new MainLoopPackage(response, MainLoopPackage.STATE.Resolved);
			outputBufferAccessor.put(aPackage);

		}).orRejected(future -> {
			Event response = Event.buildOneWay(event.getSessionId(), event.getContext(), event.getPackageId(),
					event.getData());
			MainLoopPackage aPackage = new MainLoopPackage(response, MainLoopPackage.STATE.Rejected);
			outputBufferAccessor.put(aPackage);
		});
	}

	void addEventConsumer(String sessionId, String context, EventService.EventConsumer eventConsumer) {
		if (!consumers.containsKey(sessionId)) {
			consumers.put(sessionId, Collections.synchronizedMap(new HashMap<>()));
		}
		consumers.get(sessionId).put(context, eventConsumer);
	}
}
