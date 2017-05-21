package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandBuffer;
import de.axp.portfolio.framework.CommandNotifier;
import de.axp.portfolio.framework.WorkDistributor;

class CommandWorker implements Runnable {

	private final CommandBufferImpl commandBuffer;
	private final CommandNotifier commandNotifier;

	CommandWorker(CommandBuffer commandBuffer, CommandNotifier commandNotifier) {
		this.commandBuffer = (CommandBufferImpl) commandBuffer;
		this.commandNotifier = commandNotifier;
	}

	@Override
	public void run() {
		boolean isRunning = true;
		while (isRunning) {
			String command;
			try {
				if ((command = commandBuffer.getNextCommand()) != null) {
					if (WorkDistributor.POISON.equals(command)) {
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
		commandNotifier.notifyListeners(command);
	}
}
