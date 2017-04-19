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
	@Mock
	private FrameworkInterface frameworkInterface;

	private SessionInitListener sessionInitListener;

	@Mock
	private VaadinSession vaadinSession;
	@Mock
	private SessionInitEvent sessionInitEvent;

	@Before
	public void setUp() throws Exception {
		when(frameworkFactory.getFrameworkCommandInterface()).thenReturn(frameworkInterface);
		sessionInitListener = new SessionInitListener(frameworkFactory);
		when(sessionInitEvent.getSession()).thenReturn(vaadinSession);
	}

	@Test
	public void shouldSetFrameworkInterfaceToSession() throws Exception {
		sessionInitListener.sessionInit(sessionInitEvent);

		verify(vaadinSession).setAttribute(FrameworkInterface.class.getSimpleName(), frameworkInterface);
		verify(frameworkInterface).initSession();
	}
}