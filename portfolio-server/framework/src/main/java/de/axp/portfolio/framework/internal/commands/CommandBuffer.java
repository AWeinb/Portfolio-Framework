package de.axp.portfolio.framework.internal.commands;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class CommandBuffer {

	private final BlockingQueue<CommandPacket> commandPackets = new ArrayBlockingQueue<>(10);

	void putCommand(CommandPacket commandPacket) throws InterruptedException {
		commandPackets.put(commandPacket);
	}

	CommandPacket getNextCommand() throws InterruptedException {
		return commandPackets.take();
	}
}
