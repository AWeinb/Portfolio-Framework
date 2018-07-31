package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.internal.authentication.Authentication;

import java.util.HashSet;
import java.util.Set;

class SessionServiceImpl implements SessionService {

	private final Set<String> sessionIds = new HashSet<>();

	@Override
	public void dispose() {
		sessionIds.clear();
	}

	@Override
	public FrameworkSession initializeSession(Authentication authentication) {
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
	public void checkSession(FrameworkSession session) {
	}

	@Override
	public int getActiveSessions() {
		return sessionIds.size();
	}
}