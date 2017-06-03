package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.MessageHandlerInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandHandlerNotifierTest {

	private ResponseBuffer responseBuffer;

	private CommandHandlerNotifier commandHandlerNotifier;

	private CommandPacket testCommandPacket;
	private FrameworkSessionInterface.FrameworkSession testFrameworkSession;
	private FrameworkCommandInterface.Command.CommandMessage testCommandMessage;

	private FrameworkSessionInterface.FrameworkSession respondedSession;
	private FrameworkNotice.Message respondedCommandMessage;
	private MessageHandlerInterface.ResponsePromise respondedPromiseToResolveOrReject;
	private FrameworkNotice.Message futureData;

	@Before
	public void setUp() throws Exception {
		MessageHandlerInterface messageHandlerInterface = (session, commandMessage, promiseToResolveOrReject) -> {
			respondedSession = session;
			respondedCommandMessage = commandMessage;
			respondedPromiseToResolveOrReject = promiseToResolveOrReject;
		};
		responseBuffer = new ResponseBuffer();

		commandHandlerNotifier = new CommandHandlerNotifier(messageHandlerInterface, responseBuffer);

		testFrameworkSession = new FrameworkSessionInterface.FrameworkSession() {
		};
		testCommandMessage = new FrameworkCommandInterface.Command.CommandMessage() {
		};
		CommandPacket.CommandPacketBuilder packetBuilder = new CommandPacket.CommandPacketBuilder();
		packetBuilder.setFrameworkSession(testFrameworkSession);
		packetBuilder.setCommandMessage(testCommandMessage);
		testCommandPacket = packetBuilder.build();

		futureData = new FrameworkNotice.Message() {
		};
	}

	@Test
	public void shouldPreparePromiseForHandlers() throws Exception {
		commandHandlerNotifier.notify(testCommandPacket);
		respondedPromiseToResolveOrReject.setFuture(futureData);
		respondedPromiseToResolveOrReject.resolve();

		assertEquals(testFrameworkSession, respondedSession);
		assertEquals(testCommandMessage, respondedCommandMessage);
		ResponsePacket responsePacket = responseBuffer.getNextResponse();
		checkDataIn(responsePacket);
		assertEquals(true, responsePacket.wasResolved());
	}

	@Test
	public void shouldPreparePromiseForHandlers_AlsoForRejections() throws Exception {
		commandHandlerNotifier.notify(testCommandPacket);
		respondedPromiseToResolveOrReject.setFuture(futureData);
		respondedPromiseToResolveOrReject.reject();

		assertEquals(testFrameworkSession, respondedSession);
		assertEquals(testCommandMessage, respondedCommandMessage);
		ResponsePacket responsePacket = responseBuffer.getNextResponse();
		assertEquals(true, responsePacket.wasRejected());
		checkDataIn(responsePacket);
	}

	private void checkDataIn(ResponsePacket nextResponse) throws InterruptedException {
		assertEquals(testFrameworkSession, nextResponse.getFrameworkSession());
		assertEquals(testCommandMessage, nextResponse.getCommandMessage());
		assertEquals(futureData, nextResponse.getResponseMessage());
	}
}