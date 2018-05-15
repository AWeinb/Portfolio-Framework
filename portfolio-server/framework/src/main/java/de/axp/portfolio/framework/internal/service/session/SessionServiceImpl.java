package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.FrameworkException;

import java.util.HashSet;
import java.util.Set;

class SessionServiceImpl implements SessionService {

	private final Set<String> sessionIds = new HashSet<>();

	@Override
	public void dispose() {
		sessionIds.clear();
	}

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
	public void checkID(String sessionId) {
		if (!sessionIds.contains(sessionId)) {
			throw new FrameworkSessionIsUnknownException(sessionId);
		}
	}

	@Override
	public int getActiveSessions() {
		return sessionIds.size();
	}

	@Override
	public void disposeSession(String sessionId) {
		sessionIds.remove(sessionId);
	}

	public class FrameworkSessionAlreadyUsedException extends FrameworkException {

		@Override
		public String getMessage() {
			return "Session ID is already in use!";
		}
	}

	public class FrameworkSessionIsUnknownException extends FrameworkException {

		private final String sessionId;

		public FrameworkSessionIsUnknownException(String sessionId) {
			this.sessionId = sessionId;
		}

		@Override
		public String getMessage() {
			return "Session ID is unknown! [" + sessionId + "]";
		}
	}
}