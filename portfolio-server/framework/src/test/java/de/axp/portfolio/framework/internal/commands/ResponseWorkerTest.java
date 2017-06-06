package de.axp.portfolio.framework.internal.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ResponseWorkerTest {

	private ResponseBuffer responseBuffer;
	private TestResponseNotifier responseNotifier;

	private Thread responseWorkerThread;

	@Before
	public void setUp() throws Exception {
		responseBuffer = new ResponseBuffer();
		responseNotifier = new TestResponseNotifier();
		ResponseWorker responseWorker = new ResponseWorker(responseBuffer, responseNotifier);
		responseWorkerThread = new Thread(responseWorker);
	}

	@After
	public void tearDown() throws Exception {
		responseWorkerThread.interrupt();
	}

	@Test
	public void shouldStop_WhenPoisoned() throws Exception {
		responseWorkerThread.start();
		responseBuffer.putResponse(ResponseWorker.POISON);
		Thread.sleep(50);

		assertFalse(responseWorkerThread.isAlive());
	}

	@Test
	public void shouldDelegateToNotifier() throws Exception {
		responseWorkerThread.start();
		ResponsePacket.ResponsePacketBuilder packetBuilder = new ResponsePacket.ResponsePacketBuilder();
		ResponsePacket responsePacket = packetBuilder.build();
		responseBuffer.putResponse(responsePacket);
		Thread.sleep(50);

		assertEquals(responsePacket, responseNotifier.responsePacket);
	}

	private class TestResponseNotifier extends ResponseNotifier {

		ResponsePacket responsePacket;

		@Override
		void notify(ResponsePacket responsePacket) {
			this.responsePacket = responsePacket;
		}
	}
}