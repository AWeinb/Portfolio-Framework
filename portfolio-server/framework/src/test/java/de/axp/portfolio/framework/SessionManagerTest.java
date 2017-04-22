package de.axp.portfolio.framework;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SessionManagerTest {

	private SessionManager sessionManager;

	@Before
	public void setUp() throws Exception {
		sessionManager = new SessionManager();
	}

	@Test
	public void shouldAddSessions_WhenSessionIsInitialized() throws Exception {
		sessionManager.initSession("A");
		sessionManager.initSession("B");
		sessionManager.initSession("C");

		List<String> sessions = sessionManager.getSessions();
		List<String> expected = Arrays.asList("A", "B", "C");
		assertThat(sessions, is(expected));
	}

	@Test
	public void shouldAddSessionInActiveState() throws Exception {
		sessionManager.initSession("A");

		assertEquals(SessionState.ACTIVE, sessionManager.testSessionId("A"));
	}

	@Test
	public void shouldReturnUnknown_WhenTestingUnknownSessions() throws Exception {
		assertEquals(SessionState.UNKNOWN, sessionManager.testSessionId("A"));
		assertEquals(SessionState.UNKNOWN, sessionManager.testSessionId("B"));
		assertEquals(SessionState.UNKNOWN, sessionManager.testSessionId("A"));
	}

	@Test
	public void shouldReturnUnknown_WhenSessionIsRemoved() throws Exception {
		sessionManager.initSession("A");

		sessionManager.destroySession("A");

		assertEquals(SessionState.UNKNOWN, sessionManager.testSessionId("A"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkNullSafety() throws Exception {
		sessionManager.initSession(null);
		assertEquals(SessionState.UNKNOWN, sessionManager.testSessionId(null));
		sessionManager.destroySession(null);
	}
}