package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkSessionInterface;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static de.axp.portfolio.framework.FrameworkCommandInterface.Command;

class CommandBuffer {

	private final BlockingQueue<CommandPacket> commandPackets = new ArrayBlockingQueue<>(10);

	void putCommand(CommandPacket commandPacket) throws InterruptedException {
		commandPackets.put(commandPacket);
	}

	CommandPacket getNextCommand() throws InterruptedException {
		return commandPackets.take();
	}

	interface CommandPacket {

		FrameworkSessionInterface.FrameworkSession getFrameworkSession();

		Command.CommandMessage getCommandMessage();
	}

	static class PoisonedCommandPacket implements CommandPacket {

		@Override
		public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
			return null;
		}

		@Override
		public Command.CommandMessage getCommandMessage() {
			return null;
		}
	}
}
