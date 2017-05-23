package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkMessage;

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
	public void dispatchCommand(CommandMessage commandMessage) throws InterruptedException {
		FrameworkSession session = commandMessage.getSession();
		FrameworkMessage.Message command = commandMessage.getMessage();
		FrameworkMessage.Promise promise = commandMessage.getPromise();
		commandManagement.dispatchCommand(session, command, promise);
	}
}
