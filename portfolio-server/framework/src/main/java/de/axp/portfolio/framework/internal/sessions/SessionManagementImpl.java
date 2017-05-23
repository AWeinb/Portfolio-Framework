package de.axp.portfolio.framework.internal.sessions;

import de.axp.portfolio.framework.FrameworkException;
import de.axp.portfolio.framework.internal.SessionManagement;

import java.util.ArrayList;
import java.util.List;

class SessionManagementImpl implements SessionManagement {

	private final List<String> sessionIds = new ArrayList<>();

	@Override
	public void initializeSession(String sessionId) {
		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		if (sessionIds.contains(sessionId)) {
			throw new FrameworkSessionAlreadyUsedException();
		}

		sessionIds.add(sessionId);
	}

	@Override
	public void disposeSession(String sessionId) {
		if (!sessionIds.contains(sessionId)) {
			throw new FrameworkSessionIsUnknownException();
		}

		sessionIds.remove(sessionId);
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