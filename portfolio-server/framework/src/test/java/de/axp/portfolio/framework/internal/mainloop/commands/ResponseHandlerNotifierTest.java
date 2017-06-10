package de.axp.portfolio.framework.internal.mainloop.commands;

import de.axp.portfolio.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseHandlerNotifierTest {

	private ResponseHandlerNotifier responseHandlerNotifier;

	@Before
	public void setUp() throws Exception {
		responseHandlerNotifier = new ResponseHandlerNotifier();
	}

	@Test
	public void shouldStorePromisesForCommands() throws Exception {
		FrameworkSessionInterface.FrameworkSession session = new TestFrameworkSession();
		TestMessage commandMessage = new TestMessage();
		TestCommandPromise promise = new TestCommandPromise();

		ResponsePacket.ResponsePacketBuilder responsePacketBuilder = new ResponsePacket.ResponsePacketBuilder();
		responsePacketBuilder.setFrameworkSession(session);
		responsePacketBuilder.setCommandMessage(commandMessage);
		FrameworkNotice.Message responseMessage = new TestMessage();
		responsePacketBuilder.setResponseMessage(responseMessage);

		responseHandlerNotifier.registerPromise(session, commandMessage, promise);
		responseHandlerNotifier.notify(responsePacketBuilder.build());

		assertEquals(responseMessage, promise.futureData);
	}
}