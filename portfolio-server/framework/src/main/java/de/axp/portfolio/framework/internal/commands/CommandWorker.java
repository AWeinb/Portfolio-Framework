package de.axp.portfolio.framework.internal.commands;

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
			CommandBuffer.CommandPacket commandPacket;
			try {
				if ((commandPacket = commandBuffer.getNextCommand()) != null) {
					if (commandPacket instanceof CommandBuffer.PoisonedCommandPacket) {
						isRunning = false;
					} else {
						handleCommand(commandPacket);
					}
				}
			} catch (InterruptedException e) {
				isRunning = false;
			}
		}
	}

	private void handleCommand(CommandBuffer.CommandPacket commandPacket) {
		commandHandlerNotifier.notify(commandPacket);
	}
}
