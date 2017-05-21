package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandBuffer;
import de.axp.portfolio.framework.WorkDistributor;

class CommandWorker implements Runnable {

	private final CommandBufferImpl commandBuffer;
	private final CommandListenerNotifier commandListenerNotifier;

	CommandWorker(CommandBuffer commandBuffer, CommandListenerNotifier commandListenerNotifier) {
		this.commandBuffer = (CommandBufferImpl) commandBuffer;
		this.commandListenerNotifier = commandListenerNotifier;
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
		commandListenerNotifier.notifyListeners(command);
	}
}
