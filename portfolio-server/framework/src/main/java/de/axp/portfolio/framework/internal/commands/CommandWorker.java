package de.axp.portfolio.framework.internal.commands;

class CommandWorker implements Runnable {

	static final CommandPacket POISON = new CommandPacket.CommandPacketBuilder().build();

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
			CommandPacket commandPacket;
			try {
				if ((commandPacket = commandBuffer.getNextCommand()) != null) {
					if (commandPacket == POISON) {
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

	private void handleCommand(CommandPacket commandPacket) {
		commandHandlerNotifier.notify(commandPacket);
	}
}
