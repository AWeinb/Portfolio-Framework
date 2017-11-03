package de.axp.portfolio.framework.internal.service.command;

import de.axp.portfolio.framework.api.interaction.FrameworkPackage;
import de.axp.portfolio.framework.api.interaction.FrameworkPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

class CommandListener implements MainLoop.MainLoopListener {

	private final CommandService.CommandHandler handler;
	private final MainLoop.MainLoopAccessor mainLoopAccessor;

	CommandListener(CommandService.CommandHandler handler, MainLoop.MainLoopAccessor mainLoopAccessor) {
		this.handler = handler;
		this.mainLoopAccessor = mainLoopAccessor;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		FrameworkPackage frameworkPackage = aPackage.getFrameworkPackage();

		String sessionID = frameworkPackage.getSessionID();
		String packageID = frameworkPackage.getPackageID();
		Object content = frameworkPackage.getContent();
		FrameworkPromise promiseToResolveOrReject = createPromise(frameworkPackage);

		handler.execute(sessionID, packageID, content, promiseToResolveOrReject);
	}

	private FrameworkPromise createPromise(FrameworkPackage commandPackage) {
		return new FrameworkPromise() {
			@Override
			public void resolve() {
				MainLoopPackage aPackage = new MainLoopPackage(commandPackage);
				aPackage.setState(MainLoopPackage.STATE.Resolved);
				try {
					mainLoopAccessor.put(aPackage);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void reject() {
				MainLoopPackage aPackage = new MainLoopPackage(commandPackage);
				aPackage.setState(MainLoopPackage.STATE.Rejected);
				try {
					mainLoopAccessor.put(aPackage);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
}
