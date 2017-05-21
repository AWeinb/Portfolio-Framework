package de.axp.portfolio.framework.response;

import org.junit.Before;
import org.junit.Test;

import static de.axp.portfolio.framework.response.ResponseManagementImpl.POISON;
import static org.junit.Assert.assertFalse;

public class ResponseWorkerTest {

	private ResponseBuffer responseBuffer;

	private ResponseWorker responseWorker;

	private Thread thread;

	@Before
	public void setUp() throws Exception {
		responseBuffer = new ResponseBuffer();
		responseWorker = new ResponseWorker(responseBuffer, null);
	}

	private void createAndStartThread() throws InterruptedException {
		thread = new Thread(responseWorker);
		thread.start();
		Thread.sleep(200);
	}

	@Test
	public void shouldBeStoppableByCommand() throws Exception {
		responseBuffer.putResponse(POISON);

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
		responseBuffer.putResponse("A");
		responseBuffer.putResponse("B");
		responseBuffer.putResponse("C");
		responseBuffer.putResponse(POISON);

		createAndStartThread();

		assertFalse(thread.isAlive());
	}
}