package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.VaadinSession;
import de.axp.portfolio.framework.FrameworkInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionInitListenerTest {

	@Test
	public void shouldSetFrameworkInterfaceToSession() throws Exception {
		SessionInitListener sessionInitListener = new SessionInitListener();

		SessionInitEvent sessionInitEvent = mock(SessionInitEvent.class);
		VaadinSession vaadinSession = mock(VaadinSession.class);
		when(sessionInitEvent.getSession()).thenReturn(vaadinSession);
		sessionInitListener.sessionInit(sessionInitEvent);

		verify(vaadinSession).setAttribute(eq(FrameworkInterface.class.getSimpleName()), any(FrameworkInterface.class));
	}
}