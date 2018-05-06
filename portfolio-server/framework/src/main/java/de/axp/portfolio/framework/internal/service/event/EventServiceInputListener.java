package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import static de.axp.portfolio.framework.internal.service.event.EventService.Event;
import static de.axp.portfolio.framework.internal.service.event.EventService.EventConsumer;

class EventServiceInputListener implements MainLoop.MainLoopListener {

	private final EventConsumer eventConsumer;
	private final MainLoop.MainLoopAccessor outputBufferAccessor;

	EventServiceInputListener(EventConsumer eventConsumer, MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.eventConsumer = eventConsumer;
		this.outputBufferAccessor = outputBufferAccessor;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		Event inputEvent = (Event) aPackage.getPayload();
		String sessionID = inputEvent.getSessionID();
		String packageID = inputEvent.getPackageID();
		Object content = inputEvent.getData();
		FrameworkPromise promiseToResolveOrReject = createPromise(inputEvent);

		Event consumerEvent = Event.build(sessionID, packageID, content, promiseToResolveOrReject);
		eventConsumer.consume(consumerEvent);
	}

	private FrameworkPromise createPromise(Event event) {
		return FrameworkPromise.whenResolved(future -> {
			Event response = Event.buildOneWay(event.getSessionID(), event.getPackageID(), event.getData());
			MainLoopPackage aPackage = new MainLoopPackage(response, MainLoopPackage.STATE.Resolved);
			outputBufferAccessor.put(aPackage);

		}).orRejected(future -> {
			Event response = Event.buildOneWay(event.getSessionID(), event.getPackageID(), event.getData());
			MainLoopPackage aPackage = new MainLoopPackage(response, MainLoopPackage.STATE.Rejected);
			outputBufferAccessor.put(aPackage);
		});
	}
}
