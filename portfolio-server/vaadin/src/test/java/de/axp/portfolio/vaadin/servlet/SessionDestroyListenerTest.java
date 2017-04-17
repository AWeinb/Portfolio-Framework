package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.VaadinSession;
import de.axp.portfolio.framework.FrameworkInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionDestroyListenerTest {

	private SessionDestroyEvent getSessionDestroyEventThatContainsTestFramework(TestFrameworkInterface testFrameworkInterface) {
		SessionDestroyEvent sessionDestroyEvent = mock(SessionDestroyEvent.class);
		VaadinSession vaadinSession = mock(VaadinSession.class);
		when(sessionDestroyEvent.getSession()).thenReturn(vaadinSession);
		when(vaadinSession.getAttribute(FrameworkInterface.class.getSimpleName())).thenReturn(testFrameworkInterface);
		return sessionDestroyEvent;
	}

	@Test
	public void shouldTriggerDestructionOfFramework() throws Exception {
		SessionDestroyListener sessionDestroyListener = new SessionDestroyListener();
		TestFrameworkInterface testFrameworkInterface = new TestFrameworkInterface();
		SessionDestroyEvent sessionDestroyEvent = getSessionDestroyEventThatContainsTestFramework(testFrameworkInterface);

		sessionDestroyListener.sessionDestroy(sessionDestroyEvent);

		assertTrue(testFrameworkInterface.isDestroyed);
	}

	private final class TestFrameworkInterface implements FrameworkInterface {

		private boolean isDestroyed;

		@Override
		public void initializeSession() {
		}

		@Override
		public void destroySession() {
			isDestroyed = true;
		}

		@Override
		public void putCommand(String command) throws InterruptedException {
		}

		@Override
		public void addListener(FrameworkResponseListener responseListener) {
		}
	}
}