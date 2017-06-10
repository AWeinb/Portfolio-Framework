package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkCommandInterface.Command;
import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.TestCommandPromise;
import de.axp.portfolio.framework.TestFrameworkSession;
import de.axp.portfolio.framework.internal.commands.CommandFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandManagementTest {

	private CommandManagement commandManagement;

	private FrameworkSessionInterface.FrameworkSession receivedFrameworkSession;
	private FrameworkNotice.Message receivedMessage;
	private MessageHandlerInterface.ResponsePromise receivedResponsePromise;

	private FrameworkSessionInterface.FrameworkSession sentFrameworkSession;
	private Command.CommandMessage sentCommandMessage;
	private TestCommandPromise sentCommandPromise;

	private FrameworkNotice.Message respondedFutureData;

	@Before
	public void setUp() throws Exception {
		MessageHandlerInterface messageHandlerInterface = (session, commandMessage, unresolvedResponsePromise) -> {
			receivedFrameworkSession = session;
			receivedMessage = commandMessage;
			receivedResponsePromise = unresolvedResponsePromise;
		};

		commandManagement = CommandFactory.createCommandManagement(messageHandlerInterface);
		sentFrameworkSession = new TestFrameworkSession();
		sentCommandMessage = new Command.CommandMessage() {
		};
		sentCommandPromise = new TestCommandPromise();
		respondedFutureData = new FrameworkNotice.Message() {
		};
	}

	@After
	public void tearDown() throws Exception {
		commandManagement.dispose();
	}

	@Test
	public void testStartingAndStopping() throws Exception {
		commandManagement.initialize();

		assertTrue(commandManagement.isInitialized());

		commandManagement.dispose();

		assertFalse(commandManagement.isInitialized());
	}

	@Test(expected = CommandManagement.AlreadyInitializedException.class)
	public void shouldThrowException_WhenInitializedTwice() throws Exception {
		commandManagement.initialize();
		commandManagement.initialize();
	}

	@Test
	public void shouldSendMessagesToHandler() throws Exception {
		commandManagement.initialize();
		commandManagement.dispatchCommand(sentFrameworkSession, sentCommandMessage, sentCommandPromise);
		Thread.sleep(100);
		commandManagement.dispose();

		assertEquals(sentFrameworkSession, receivedFrameworkSession);
		assertEquals(sentCommandMessage, receivedMessage);
	}

	@Test
	public void shouldGetResponse_WhenResolvingReceivedPromise() throws Exception {
		commandManagement.initialize();
		commandManagement.dispatchCommand(sentFrameworkSession, sentCommandMessage, sentCommandPromise);
		Thread.sleep(100);
		receivedResponsePromise.setFuture(respondedFutureData);
		receivedResponsePromise.resolve();
		Thread.sleep(100);
		commandManagement.dispose();

		assertTrue(sentCommandPromise.resolved);
		assertEquals(respondedFutureData, sentCommandPromise.futureData);
	}

	@Test
	public void shouldGetResponse_WhenRejectingReceivedPromise() throws Exception {
		commandManagement.initialize();
		commandManagement.dispatchCommand(sentFrameworkSession, sentCommandMessage, sentCommandPromise);
		Thread.sleep(100);
		receivedResponsePromise.reject();
		Thread.sleep(100);
		commandManagement.dispose();

		assertTrue(sentCommandPromise.rejected);
	}
}