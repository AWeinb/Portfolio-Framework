package de.axp.portfolio.framework;

import org.junit.Before;
import org.junit.Test;

public class FrameworkInterfaceImplTest {

	private FrameworkInterfaceImpl frameworkInterface;

	@Before
	public void setUp() throws Exception {
		frameworkInterface = new FrameworkInterfaceImpl();
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
}