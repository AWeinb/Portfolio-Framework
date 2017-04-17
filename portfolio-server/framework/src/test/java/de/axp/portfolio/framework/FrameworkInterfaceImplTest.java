package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.CommandFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FrameworkInterfaceImplTest {

	private CommandBuffer commandBuffer;
	private FrameworkInterfaceImpl frameworkInterface;

	@Before
	public void setUp() throws Exception {
		commandBuffer = CommandFactory.createCommandBuffer();
		frameworkInterface = new FrameworkInterfaceImpl(commandBuffer);
	}

	@Test(expected = FrameworkInterfaceImpl.FrameworkAlreadyInitializedException.class)
	public void frameworkCanNotBeInitializedMoreThanOnce() throws Exception {
		frameworkInterface.initializeSession();
		frameworkInterface.initializeSession();
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
	public void frameworkTakesCommands() throws Exception {
		frameworkInterface.initializeSession();
		frameworkInterface.putCommand("command1");
		frameworkInterface.putCommand("command2");
		frameworkInterface.putCommand("command3");
		frameworkInterface.putCommand("command4");
	}
}