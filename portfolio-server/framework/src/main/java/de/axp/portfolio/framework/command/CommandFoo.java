package de.axp.portfolio.framework.command;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class CommandFoo {

	private BlockingQueue<String> commands = new ArrayBlockingQueue<>(10);

	void putCommand(String command) throws InterruptedException {
		System.err.println("putCommand: " + command);
		commands.put(command);
	}

	String takeCommand() throws InterruptedException {
		String take = commands.take();
		System.err.println("takeCommand: " + take);
		return take;
	}
}
