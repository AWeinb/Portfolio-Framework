package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkNotice;

import static de.axp.portfolio.framework.FrameworkSessionInterface.FrameworkSession;

class FrameworkCommandInterfaceImpl implements FrameworkCommandInterface {

	private final CommandManagement commandManagement;

	FrameworkCommandInterfaceImpl(CommandManagement commandManagement) {
		this.commandManagement = commandManagement;
	}

	@Override
	public void initialize() {
		commandManagement.initialize();
	}

	@Override
	public void dispose() {
		try {
			commandManagement.dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispatchCommand(Command command) throws InterruptedException {
		FrameworkSession session = command.getSession();
		Command.CommandMessage message = command.getMessage();
		FrameworkNotice.Promise promise = command.getPromise();
		commandManagement.dispatchCommand(session, message, promise);
	}
}
