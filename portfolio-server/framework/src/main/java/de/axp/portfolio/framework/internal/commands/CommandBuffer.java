package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkMessage;
import de.axp.portfolio.framework.FrameworkSessionInterface;

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

	interface CommandPacket {

		FrameworkSessionInterface.FrameworkSession getFrameworkSession();

		FrameworkMessage.Message getCommand();
	}

	static class PoisonedCommandPacket implements CommandPacket {

		@Override
		public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
			return null;
		}

		@Override
		public FrameworkMessage.Message getCommand() {
			return null;
		}
	}
}
