package de.axp.portfolio.framework;

class FrameworkCommandInterfaceImpl implements FrameworkCommandInterface {

	private final CommandManagement commandManagement;

	FrameworkCommandInterfaceImpl(CommandManagement commandManagement) {
		this.commandManagement = commandManagement;
	}

	@Override
	public void putCommand(String command) throws InterruptedException {
		commandManagement.dispatchCommand(command);
	}
}
