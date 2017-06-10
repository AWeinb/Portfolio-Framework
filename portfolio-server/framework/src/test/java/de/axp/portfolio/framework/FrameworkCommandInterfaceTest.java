package de.axp.portfolio.framework;

import de.axp.portfolio.framework.internal.InternalFrameworkFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FrameworkCommandInterfaceTest {

	private FrameworkCommandInterface frameworkCommandInterface;

	@Before
	public void setUp() throws Exception {
		Map<Class, FrameworkInterface> frameworkInterfaces = InternalFrameworkFactory.createFrameworkInterfaces();
		FrameworkInterface frameworkInterface = frameworkInterfaces.get(FrameworkCommandInterface.class);
		frameworkCommandInterface = (FrameworkCommandInterface) frameworkInterface;
	}

	@Test
	public void testRoundTripThroughWorkers() throws Exception {
		FrameworkSessionInterface.FrameworkSession session = new TestFrameworkSession();
		TestCommandPromise testCommandPromise = new TestCommandPromise();
		TestMessage message = new TestMessage();
		TestCommand testCommand = new TestCommand(session, message, testCommandPromise);

		frameworkCommandInterface.dispatchCommand(testCommand);
		Thread.sleep(100);
		frameworkCommandInterface.dispose();

		assertTrue(testCommandPromise.resolved || testCommandPromise.rejected || testCommandPromise.futureData != null);
	}
}