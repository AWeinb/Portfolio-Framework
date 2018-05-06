package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.FrameworkPackage;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopBufferException;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

class EventServiceInputListener implements MainLoop.MainLoopListener {

	private final EventService.EventHandler eventHandler;
	private final MainLoop.MainLoopAccessor inputBufferAccessor;

	EventServiceInputListener(EventService.EventHandler eventHandler, MainLoop.MainLoopAccessor inputBufferAccessor) {
		this.eventHandler = eventHandler;
		this.inputBufferAccessor = inputBufferAccessor;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		Event event = (Event) aPackage.getFrameworkPackage();

		String sessionID = event.getSessionID();
		String packageID = event.getPackageID();
		Object content = event.getContent();
		FrameworkPromise promiseToResolveOrReject = createPromise(event);

		eventHandler.execute(sessionID, packageID, content, promiseToResolveOrReject);
	}

	private FrameworkPromise createPromise(FrameworkPackage commandPackage) {
		return new FrameworkPromise() {
			@Override
			public void resolve() {
				Response response = new Response();
				response.setSessionID(commandPackage.getSessionID());
				response.setPackageID(commandPackage.getPackageID());
				response.setContent(commandPackage.getContent());

				MainLoopPackage aPackage = new MainLoopPackage(response);
				aPackage.setState(MainLoopPackage.STATE.Resolved);
				try {
					inputBufferAccessor.put(aPackage);
				} catch (InterruptedException e) {
					handleException(e);
				}
			}

			@Override
			public void reject() {
				Response response = new Response();
				response.setSessionID(commandPackage.getSessionID());
				response.setPackageID(commandPackage.getPackageID());
				response.setContent(commandPackage.getContent());

				MainLoopPackage aPackage = new MainLoopPackage(response);
				aPackage.setState(MainLoopPackage.STATE.Rejected);
				try {
					inputBufferAccessor.put(aPackage);
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
