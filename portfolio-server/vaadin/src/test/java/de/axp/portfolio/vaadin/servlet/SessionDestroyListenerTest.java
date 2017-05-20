package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.VaadinSession;
import de.axp.portfolio.framework.FrameworkInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionDestroyListenerTest {

	private SessionDestroyListener sessionDestroyListener;

	@Mock
	private SessionDestroyEvent sessionDestroyEvent;
	@Mock
	private VaadinSession vaadinSession;
	@Mock
	private FrameworkInterface frameworkInterface;

	@Before
	public void setUp() throws Exception {
		when(sessionDestroyEvent.getSession()).thenReturn(vaadinSession);
		when(vaadinSession.getAttribute(FrameworkInterface.class.getSimpleName())).thenReturn(frameworkInterface);

		sessionDestroyListener = new SessionDestroyListener();
	}

	@Test
	public void shouldTriggerDestructionOfSession() throws Exception {
		when(vaadinSession.getAttribute("ID")).thenReturn("sessionID");

		sessionDestroyListener.sessionDestroy(sessionDestroyEvent);

		verify(sessionDestroyEvent.getSession()).setAttribute(FrameworkInterface.class.getSimpleName(), null);
		verify(sessionDestroyEvent.getSession()).setAttribute("ID", null);
		verify(frameworkInterface).destroySession("sessionID");
	}

	@Test
	public void shouldKeepFrameworkActive_WhenSessionsAreActive() throws Exception {
		when(frameworkInterface.hasFrameworkActiveSessions()).thenReturn(true);

		sessionDestroyListener.sessionDestroy(sessionDestroyEvent);

		verify(frameworkInterface, never()).disposeFramework();
	}

	@Test
	public void shouldDestroyFramework_WhenNoSessionsLeft() throws Exception {
		when(frameworkInterface.hasFrameworkActiveSessions()).thenReturn(false);

		sessionDestroyListener.sessionDestroy(sessionDestroyEvent);

		verify(frameworkInterface).disposeFramework();
	}
}