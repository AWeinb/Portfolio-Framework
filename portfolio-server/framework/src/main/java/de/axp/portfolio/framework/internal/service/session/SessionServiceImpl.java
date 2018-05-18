package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.FrameworkAuthentication;
import de.axp.portfolio.framework.api.FrameworkException;
import de.axp.portfolio.framework.api.FrameworkSession;

import java.util.HashSet;
import java.util.Set;

class SessionServiceImpl implements SessionService {

	private final Set<String> sessionIds = new HashSet<>();

	@Override
	public void dispose() {
		sessionIds.clear();
	}

	@Override
	public FrameworkSession initializeSession(FrameworkAuthentication authentication) {
		if (authentication == null) {
			throw new IllegalArgumentException();
		}
		return new FrameworkSession() {
		};
	}

	@Override
	public void invalidateSession(FrameworkSession session) {
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