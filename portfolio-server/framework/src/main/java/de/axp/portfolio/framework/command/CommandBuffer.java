package de.axp.portfolio.framework.command;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class CommandBuffer {

	private final BlockingQueue<String> commands = new ArrayBlockingQueue<>(10);

	void putCommand(String command) throws InterruptedException {
		commands.put(command);
	}

	String getNextCommand() throws InterruptedException {
		return commands.take();
	}
}
