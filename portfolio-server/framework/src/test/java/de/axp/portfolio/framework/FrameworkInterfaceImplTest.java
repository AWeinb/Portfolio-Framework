package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameworkInterfaceImplTest {

	private ResponseNotifier responseNotifier;
	private TestWorkDistributor testWorkDistributor;
	private FrameworkInterfaceImpl frameworkInterface;

	@Before
	public void setUp() throws Exception {
		CommandBuffer commandBuffer = CommandFactory.createCommandBuffer();
		CommandListenerNotifier commandListenerNotifier = CommandFactory.createCommandListenerNotifier();
		responseNotifier = CommandFactory.createResponseNotifier();
		testWorkDistributor = new TestWorkDistributor();
		SessionManager sessionManager = FrameworkFactory.createSessionManager();
		frameworkInterface = new FrameworkInterfaceImpl(commandBuffer, commandListenerNotifier, responseNotifier,
				testWorkDistributor, sessionManager);
	}

	@Test(expected = FrameworkInterfaceImpl.FrameworkAlreadyInitializedException.class)
	public void frameworkCanNotBeInitializedMoreThanOnce() throws Exception {
		frameworkInterface.initFramework();
		frameworkInterface.initFramework();
	}

	@Test
	public void whenInitialized_ThenTheWorkerIsStarted() throws Exception {
		frameworkInterface.initFramework();

		assertTrue(testWorkDistributor.started);
	}

	@Test
	public void frameworkCanBeDestroyed() throws Exception {
		frameworkInterface.initFramework();
		frameworkInterface.disposeFramework();
	}

	@Test(expected = FrameworkInterfaceImpl.FrameworkNotInitializedException.class)
	public void frameworkCanOnlyBeDestroyedIfInitialized() throws Exception {
		frameworkInterface.disposeFramework();
	}

	@Test(expected = FrameworkInterfaceImpl.FrameworkNotInitializedException.class)
	public void frameworkCanOnlyBeDestroyedOnce() throws Exception {
		frameworkInterface.disposeFramework();
		frameworkInterface.disposeFramework();
	}

	@Test
	public void whenTheFrameworkIsDestroyed_ThenTheWorkerIsStopped() throws Exception {
		frameworkInterface.initFramework();
		frameworkInterface.disposeFramework();

		assertFalse(testWorkDistributor.started);
	}

	@Test
	public void frameworkTakesCommands() throws Exception {
		frameworkInterface.initFramework();

		frameworkInterface.putCommand("command1");
		frameworkInterface.putCommand("command2");
		frameworkInterface.putCommand("command3");
		frameworkInterface.putCommand("command4");
	}

	@Test(expected = SessionManager.FrameworkSessionAlreadyUsedException.class)
	public void frameworkCreatesSessionsWithUniqueID() throws Exception {
		frameworkInterface.initSession("ID1");
		frameworkInterface.initSession("ID1");
	}

	@Test
	public void frameworkCanHandleMultipleSessions() throws Exception {
		frameworkInterface.initSession("ID1");
		frameworkInterface.initSession("ID2");
		frameworkInterface.initSession("ID3");

		assertEquals(SessionState.ACTIVE, frameworkInterface.testSessionId("ID1"));
		assertEquals(SessionState.ACTIVE, frameworkInterface.testSessionId("ID2"));
		assertEquals(SessionState.ACTIVE, frameworkInterface.testSessionId("ID3"));

		assertEquals(SessionState.UNKNOWN, frameworkInterface.testSessionId("ID4"));
	}

	@Test
	public void frameworkCanDestroySessions() throws Exception {
		frameworkInterface.initSession("ID1");
		frameworkInterface.initSession("ID2");

		frameworkInterface.destroySession("ID1");
		frameworkInterface.destroySession("ID2");

		assertEquals(SessionState.UNKNOWN, frameworkInterface.testSessionId("ID1"));
		assertEquals(SessionState.UNKNOWN, frameworkInterface.testSessionId("ID2"));
	}

	@Test(expected = SessionManager.FrameworkSessionIsUnknownException.class)
	public void frameworkCanNotDestroyUnknownSessions() throws Exception {
		frameworkInterface.destroySession("X");
	}

	@Test
	public void frameworkTakesResponseListenersAndNotifiesThem() throws Exception {
		frameworkInterface.initFramework();

		TestFrameworkResponseListener responseListener = new TestFrameworkResponseListener();
		frameworkInterface.addListener(responseListener);
		responseNotifier.notifyListeners(null);

		assertTrue(responseListener.executed);
	}

	private class TestWorkDistributor implements WorkDistributor {

		private boolean started;

		@Override
		public void initWorkers() {
			started = true;
		}

		@Override
		public void stopWorkers() {
			started = false;
		}

		@Override
		public boolean isWorking() {
			return started;
		}
	}

	private class TestFrameworkResponseListener implements FrameworkInterface.FrameworkResponseListener {

		boolean executed;

		@Override
		public void onResponse(String response) {
			executed = true;
		}
	}
}