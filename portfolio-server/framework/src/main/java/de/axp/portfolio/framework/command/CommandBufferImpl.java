package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandBuffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class CommandBufferImpl implements CommandBuffer {

	private final BlockingQueue<String> commands = new ArrayBlockingQueue<>(10);

	@Override
	public void putCommand(String command) throws InterruptedException {
		commands.put(command);
	}

	String getNextCommand() throws InterruptedException {
		return commands.take();
	}
}
