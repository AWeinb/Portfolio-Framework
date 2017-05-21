package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandManagement;

class CommandManagementImpl implements CommandManagement {

	static final String POISON = "POISON";

	private final CommandBuffer commandBuffer;
	private final CommandHandlerNotifier commandHandlerNotifier;

	private Thread commandWorkerThread;

	CommandManagementImpl(CommandBuffer commandBuffer, CommandHandlerNotifier commandHandlerNotifier) {
		this.commandBuffer = commandBuffer;
		this.commandHandlerNotifier = commandHandlerNotifier;
	}

	private void initialize() {
		commandWorkerThread = new Thread(new CommandWorker(commandBuffer, commandHandlerNotifier));
		commandWorkerThread.start();
	}

	private void dispose() {
		try {
			commandBuffer.putCommand(POISON);
			commandWorkerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispatchCommand(String command) {

	}
}
