package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.VaadinSession;
import de.axp.portfolio.framework.FrameworkFactory;
import de.axp.portfolio.framework.FrameworkInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionInitListenerTest {

	@Mock
	private FrameworkFactory frameworkFactory;

	private SessionInitListener sessionInitListener;
	@Mock
	private FrameworkInterface frameworkInterface;
	@Mock
	private VaadinSession vaadinSession;
	@Mock
	private SessionInitEvent sessionInitEvent;

	@Before
	public void setUp() throws Exception {
		when(frameworkFactory.getFrameworkCommandInterface()).thenReturn(frameworkInterface);
		SessionIdComputation sessionIdComputation = new SessionIdComputation();

		sessionInitListener = new SessionInitListener(frameworkFactory, sessionIdComputation);
		when(sessionInitEvent.getSession()).thenReturn(vaadinSession);
	}

	@Test
	public void shouldSetFrameworkInterfaceToSession() throws Exception {
		String sessionId = "0";
		sessionInitListener.sessionInit(sessionInitEvent);

		verify(vaadinSession).setAttribute(FrameworkInterface.class.getSimpleName(), frameworkInterface);
		verify(vaadinSession).setAttribute("ID", sessionId);
		verify(frameworkInterface).initSession(sessionId);
	}
}