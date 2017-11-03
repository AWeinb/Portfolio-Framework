package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.interaction.FrameworkException;

import java.util.HashSet;
import java.util.Set;

class SessionServiceImpl implements SessionService {

	private final Set<String> sessionIds = new HashSet<>();

	@Override
	public void dispose() {
		sessionIds.clear();
	}

	@Override
	public void initializeSession(String sessionID) {
		if (sessionID == null) {
			throw new IllegalArgumentException();
		}
		if (sessionIds.contains(sessionID)) {
			throw new FrameworkSessionAlreadyUsedException();
		}

		sessionIds.add(sessionID);
	}

	@Override
	public void checkID(String sessionID) {
		if (!sessionIds.contains(sessionID)) {
			throw new FrameworkSessionIsUnknownException(sessionID);
		}
	}

	@Override
	public int getActiveSessions() {
		return sessionIds.size();
	}

	@Override
	public void disposeSession(String sessionID) {
		if (!sessionIds.contains(sessionID)) {
			throw new FrameworkSessionIsUnknownException(sessionID);
		}

		sessionIds.remove(sessionID);
	}

	public class FrameworkSessionAlreadyUsedException extends FrameworkException {

		@Override
		public String getMessage() {
			return "Session ID is already in use!";
		}
	}

	public class FrameworkSessionIsUnknownException extends FrameworkException {

		private final String sessionID;

		public FrameworkSessionIsUnknownException(String sessionID) {
			this.sessionID = sessionID;
		}

		@Override
		public String getMessage() {
			return "Session ID is unknown! [" + sessionID + "]";
		}
	}
}