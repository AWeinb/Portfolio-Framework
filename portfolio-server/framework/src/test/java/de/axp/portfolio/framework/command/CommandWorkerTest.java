package de.axp.portfolio.framework.command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CommandWorkerTest {

	private CommandBufferImpl commandBuffer;
	private ResponseBuffer responseBuffer;

	private CommandWorker commandWorker;

	private Thread thread;

	@Before
	public void setUp() throws Exception {
		commandBuffer = new CommandBufferImpl();
		responseBuffer = new ResponseBuffer();
		commandWorker = new CommandWorker(commandBuffer, responseBuffer);
	}

	private void createAndStartThread() throws InterruptedException {
		thread = new Thread(commandWorker);
		thread.start();
		Thread.sleep(200);
	}

	@Test
	public void shouldBeStoppableByCommand() throws Exception {
		commandBuffer.putCommand(WorkDistributor.POISON);

		createAndStartThread();

		assertFalse(thread.isAlive());
	}

	@Test
	public void shouldGracefullyHandleInterrupt() throws Exception {
		createAndStartThread();

		thread.interrupt();
		Thread.sleep(50);

		assertFalse(thread.isAlive());
	}

	@Test
	public void shouldCopyCommandsToResponseBuffer() throws Exception {
		commandBuffer.putCommand("A");
		commandBuffer.putCommand("B");
		commandBuffer.putCommand("C");
		commandBuffer.putCommand(WorkDistributor.POISON);

		createAndStartThread();

		assertFalse(thread.isAlive());
		assertEquals("A", responseBuffer.getNextResponse());
		assertEquals("B", responseBuffer.getNextResponse());
		assertEquals("C", responseBuffer.getNextResponse());
	}
}