package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopBufferException;
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
		Object content = inputEvent.getContent();
		FrameworkPromise promiseToResolveOrReject = createPromise(inputEvent);

		Event consumerEvent = Event.build(sessionID, packageID, content, promiseToResolveOrReject);
		eventConsumer.consume(consumerEvent);
	}

	private FrameworkPromise createPromise(Event event) {
		return new FrameworkPromise() {
			@Override
			public void resolve() {
				Event response = Event.buildOneWay(event.getSessionID(), event.getPackageID(), event.getContent());
				MainLoopPackage aPackage = new MainLoopPackage(response, MainLoopPackage.STATE.Resolved);
				try {
					outputBufferAccessor.put(aPackage);
				} catch (InterruptedException e) {
					handleException(e);
				}
			}

			@Override
			public void reject() {
				Event response = Event.buildOneWay(event.getSessionID(), event.getPackageID(), event.getContent());
				MainLoopPackage aPackage = new MainLoopPackage(response, MainLoopPackage.STATE.Rejected);
				try {
					outputBufferAccessor.put(aPackage);
				} catch (InterruptedException e) {
					handleException(e);
				}
			}
		};
	}

	private void handleException(InterruptedException e) {
		throw new MainLoopBufferException(e);
	}
}
