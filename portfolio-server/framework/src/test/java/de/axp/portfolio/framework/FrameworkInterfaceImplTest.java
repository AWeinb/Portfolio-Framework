package de.axp.portfolio.framework;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FrameworkInterfaceImplTest {

	@Mock
	private CommandManagement commandManagement;
	@Mock
	private ResponseManagement responseManagement;
	@Mock
	private SessionManager sessionManager;

	private FrameworkInterfaceImpl frameworkInterface;

	@Before
	public void setUp() throws Exception {
		frameworkInterface = new FrameworkInterfaceImpl(sessionManager);
	}

	@Test
	public void testInitializationOfSessions() throws Exception {
		frameworkInterface.initSession("id");

		verify(sessionManager).initSession("id");
	}

	@Test
	public void testDestructionOfSessions() throws Exception {
		frameworkInterface.destroySession("id");

		verify(sessionManager).destroySession("id");
	}
}