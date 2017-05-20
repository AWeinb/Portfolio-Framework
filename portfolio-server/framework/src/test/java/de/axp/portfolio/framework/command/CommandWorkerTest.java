package de.axp.portfolio.framework.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandWorkerTest {

	private CommandBufferImpl commandBuffer;
	@Mock
	private CommandListenerNotifier commandListenerNotifier;

	private CommandWorker commandWorker;

	private Thread thread;

	@Before
	public void setUp() throws Exception {
		commandBuffer = new CommandBufferImpl();
		commandWorker = new CommandWorker(commandBuffer, commandListenerNotifier);
	}

	private void createAndStartThread() {
		thread = new Thread(commandWorker);
		thread.start();
	}

	@Test
	public void shouldBeStoppableByCommand() throws Exception {
		commandBuffer.putCommand(WorkDistributor.POISON);

		createAndStartThread();
		Thread.sleep(50);

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
	public void shouldCallNotifier() throws Exception {
		commandBuffer.putCommand("A");
		commandBuffer.putCommand(WorkDistributor.POISON);

		createAndStartThread();
		Thread.sleep(50);

		verify(commandListenerNotifier).notifyListeners("A");
	}
}