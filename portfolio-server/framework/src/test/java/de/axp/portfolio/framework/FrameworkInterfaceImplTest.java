package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.CommandFactory;
import de.axp.portfolio.framework.command.ResponseNotifier;
import de.axp.portfolio.framework.command.WorkDistributor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FrameworkInterfaceImplTest {

	private ResponseNotifier responseNotifier;
	private TestWorkDistributor testWorkDistributor;
	private FrameworkInterfaceImpl frameworkInterface;

	@Before
	public void setUp() throws Exception {
		CommandBuffer commandBuffer = CommandFactory.createCommandBuffer();
		responseNotifier = CommandFactory.createResponseNotifier();
		testWorkDistributor = new TestWorkDistributor();
		frameworkInterface = new FrameworkInterfaceImpl(commandBuffer, responseNotifier, testWorkDistributor);
	}

	@Test(expected = FrameworkInterfaceImpl.FrameworkAlreadyInitializedException.class)
	public void frameworkCanNotBeInitializedMoreThanOnce() throws Exception {
		frameworkInterface.initializeSession();
		frameworkInterface.initializeSession();
	}

	@Test
	public void whenInitialized_ThenTheWorkerIsStarted() throws Exception {
		frameworkInterface.initializeSession();

		assertTrue(testWorkDistributor.started);
	}

	@Test
	public void frameworkCanBeDestroyed() throws Exception {
		frameworkInterface.initializeSession();
		frameworkInterface.destroySession();
	}

	@Test(expected = FrameworkInterfaceImpl.FrameworkNotInitializedException.class)
	public void frameworkCanOnlyBeDestroyedIfInitialized() throws Exception {
		frameworkInterface.destroySession();
	}

	@Test(expected = FrameworkInterfaceImpl.FrameworkNotInitializedException.class)
	public void frameworkCanOnlyBeDestroyedOnce() throws Exception {
		frameworkInterface.destroySession();
		frameworkInterface.destroySession();
	}

	@Test
	public void whenTheSessionIsDestroyed_ThenTheWorkerIsStopped() throws Exception {
		frameworkInterface.initializeSession();
		frameworkInterface.destroySession();

		assertFalse(testWorkDistributor.started);
	}

	@Test
	public void frameworkTakesCommands() throws Exception {
		frameworkInterface.initializeSession();

		frameworkInterface.putCommand("command1");
		frameworkInterface.putCommand("command2");
		frameworkInterface.putCommand("command3");
		frameworkInterface.putCommand("command4");
	}

	@Test
	public void frameworkTakesResponseListenersAndNotifiesThem() throws Exception {
		frameworkInterface.initializeSession();

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
	}

	private class TestFrameworkResponseListener implements FrameworkInterface.FrameworkResponseListener {

		boolean executed;

		@Override
		public void onResponse(String response) {
			executed = true;
		}
	}
}