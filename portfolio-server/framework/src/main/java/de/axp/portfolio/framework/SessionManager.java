package de.axp.portfolio.framework;

import java.util.ArrayList;
import java.util.List;

class SessionManager {

	private final List<String> sessionIds = new ArrayList<>();

	void initSession(String sessionId) {
		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		if (sessionIds.contains(sessionId)) {
			throw new FrameworkSessionAlreadyUsedException();
		}

		sessionIds.add(sessionId);
	}

	SessionState testSessionId(String sessionId) {
		return sessionIds.contains(sessionId) ? SessionState.ACTIVE : SessionState.UNKNOWN;
	}

	void destroySession(String sessionId) {
		if (!sessionIds.contains(sessionId)) {
			throw new FrameworkSessionIsUnknownException();
		}

		sessionIds.remove(sessionId);
	}

	List<String> getSessions() {
		return sessionIds;
	}

	public class FrameworkSessionAlreadyUsedException extends FrameworkException {

		@Override
		public String getMessage() {
			return "Session ID is already in use!";
		}
	}

	public class FrameworkSessionIsUnknownException extends FrameworkException {
		@Override
		public String getMessage() {
			return "Session ID is unknown!";
		}
	}
}