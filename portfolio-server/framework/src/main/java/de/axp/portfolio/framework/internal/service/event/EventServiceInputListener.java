package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.FrameworkPackage;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopBufferException;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

class EventServiceInputListener implements MainLoop.MainLoopListener {

	private final EventService.EventConsumer eventConsumer;
	private final MainLoop.MainLoopAccessor outputBufferAccessor;

	EventServiceInputListener(EventService.EventConsumer eventConsumer,
	                          MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.eventConsumer = eventConsumer;
		this.outputBufferAccessor = outputBufferAccessor;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		Event inputEvent = (Event) aPackage.getFrameworkPackage();
		String sessionID = inputEvent.getSessionID();
		String packageID = inputEvent.getPackageID();
		Object content = inputEvent.getContent();
		FrameworkPromise promiseToResolveOrReject = createPromise(inputEvent);

		Event consumerEvent = new Event(sessionID, packageID, content, promiseToResolveOrReject);
		eventConsumer.consume(consumerEvent);
	}

	private FrameworkPromise createPromise(FrameworkPackage commandPackage) {
		return new FrameworkPromise() {
			@Override
			public void resolve() {
				Event response = new Event(commandPackage.getSessionID(), commandPackage.getPackageID(),
						commandPackage.getContent(), null);
				MainLoopPackage aPackage = new MainLoopPackage(response);
				aPackage.setState(MainLoopPackage.STATE.Resolved);
				try {
					outputBufferAccessor.put(aPackage);
				} catch (InterruptedException e) {
					handleException(e);
				}
			}

			@Override
			public void reject() {
				Event response = new Event(commandPackage.getSessionID(), commandPackage.getPackageID(),
						commandPackage.getContent(), null);
				MainLoopPackage aPackage = new MainLoopPackage(response);
				aPackage.setState(MainLoopPackage.STATE.Rejected);
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
