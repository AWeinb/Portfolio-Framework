package de.axp.portfolio.framework.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static de.axp.portfolio.framework.command.CommandManagementImpl.POISON;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandWorkerTest {

	private CommandBuffer commandBuffer;
	@Mock
	private CommandHandlerNotifier commandHandlerNotifier;

	private CommandWorker commandWorker;

	private Thread thread;

	@Before
	public void setUp() throws Exception {
		commandBuffer = new CommandBuffer();
		commandWorker = new CommandWorker(commandBuffer, commandHandlerNotifier);
	}

	private void createAndStartThread() {
		thread = new Thread(commandWorker);
		thread.start();
	}

	@Test
	public void shouldBeStoppableByCommand() throws Exception {
		commandBuffer.putCommand(POISON);

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
		commandBuffer.putCommand(POISON);

		createAndStartThread();
		Thread.sleep(50);

		verify(commandHandlerNotifier).notifyListeners("A");
	}
}