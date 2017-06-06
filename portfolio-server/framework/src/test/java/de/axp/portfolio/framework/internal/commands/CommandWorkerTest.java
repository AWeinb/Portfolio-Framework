package de.axp.portfolio.framework.internal.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CommandWorkerTest {

	private CommandBuffer commandBuffer;
	private TestCommandHandlerNotifier commandHandlerNotifier;

	private Thread commandWorkerThread;

	@Before
	public void setUp() throws Exception {
		commandBuffer = new CommandBuffer();
		commandHandlerNotifier = new TestCommandHandlerNotifier();
		CommandWorker commandWorker = new CommandWorker(commandBuffer, commandHandlerNotifier);
		commandWorkerThread = new Thread(commandWorker);
	}

	@After
	public void tearDown() throws Exception {
		commandWorkerThread.interrupt();
	}

	@Test
	public void shouldStop_WhenPoisoned() throws Exception {
		commandWorkerThread.start();
		commandBuffer.putCommand(CommandWorker.POISON);
		Thread.sleep(50);

		assertFalse(commandWorkerThread.isAlive());
	}

	@Test
	public void shouldDelegateToNotifier() throws Exception {
		commandWorkerThread.start();
		CommandPacket.CommandPacketBuilder commandPacketBuilder = new CommandPacket.CommandPacketBuilder();
		CommandPacket commandPacket = commandPacketBuilder.build();
		commandBuffer.putCommand(commandPacket);
		Thread.sleep(50);

		assertEquals(commandPacket, commandHandlerNotifier.commandPacket);
	}

	private class TestCommandHandlerNotifier extends CommandHandlerNotifier {

		CommandPacket commandPacket;

		TestCommandHandlerNotifier() {
			super(null, null);
		}

		@Override
		void notify(CommandPacket commandPacket) {
			this.commandPacket = commandPacket;
		}
	}
}