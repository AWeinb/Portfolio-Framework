package de.axp.portfolio.framework.command;

import static de.axp.portfolio.framework.command.CommandManagementImpl.POISON;

class CommandWorker implements Runnable {

	private final CommandBuffer commandBuffer;
	private final CommandHandlerNotifier commandHandlerNotifier;

	CommandWorker(CommandBuffer commandBuffer, CommandHandlerNotifier commandHandlerNotifier) {
		this.commandBuffer = commandBuffer;
		this.commandHandlerNotifier = commandHandlerNotifier;
	}

	@Override
	public void run() {
		boolean isRunning = true;
		while (isRunning) {
			String command;
			try {
				if ((command = commandBuffer.getNextCommand()) != null) {
					if (POISON.equals(command)) {
						isRunning = false;
					} else {
						handleCommand(command);
					}
				}
			} catch (InterruptedException e) {
				isRunning = false;
			}
		}
	}

	private void handleCommand(String command) {
		commandHandlerNotifier.notifyListeners(command);
	}
}
