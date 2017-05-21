package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.FrameworkInterface;
import de.axp.portfolio.framework.ResponseNotifier;
import de.axp.portfolio.framework.WorkDistributor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ResponseWorkerTest {

	private ResponseBuffer responseBuffer;
	private TestResponseNotifier testResponseNotifier;

	private ResponseWorker responseWorker;

	private Thread thread;

	@Before
	public void setUp() throws Exception {
		responseBuffer = new ResponseBuffer();
		testResponseNotifier = new TestResponseNotifier();
		responseWorker = new ResponseWorker(responseBuffer, testResponseNotifier);
	}

	private void createAndStartThread() throws InterruptedException {
		thread = new Thread(responseWorker);
		thread.start();
		Thread.sleep(200);
	}

	@Test
	public void shouldBeStoppableByCommand() throws Exception {
		responseBuffer.putResponse(WorkDistributor.POISON);

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
		responseBuffer.putResponse(WorkDistributor.POISON);

		createAndStartThread();

		assertFalse(thread.isAlive());
		assertEquals("A", testResponseNotifier.responses.get(0));
		assertEquals("B", testResponseNotifier.responses.get(1));
		assertEquals("C", testResponseNotifier.responses.get(2));
	}

	private class TestResponseNotifier implements ResponseNotifier {

		final List<String> responses = new ArrayList<>();

		@Override
		public void addResponseListener(FrameworkInterface.FrameworkResponseListener responseListener) {

		}

		@Override
		public void notifyListeners(String response) {
			responses.add(response);
		}
	}
}