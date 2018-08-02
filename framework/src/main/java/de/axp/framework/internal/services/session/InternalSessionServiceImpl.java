package de.axp.framework.internal.services.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.authentication.Authentication;

import java.util.HashSet;
import java.util.Set;

class InternalSessionServiceImpl implements InternalSessionService {

	private final Set<String> sessionIds = new HashSet<>();

	@Override
	public SessionService.FrameworkSession initializeSession(Authentication authentication) {
		if (authentication == null) {
			throw new IllegalArgumentException();
		}
		return new SessionService.FrameworkSession() {
		};
	}

	@Override
	public void invalidateSession(SessionService.FrameworkSession session) {
	}

	@Override
	public void checkSession(SessionService.FrameworkSession session) {
	}

	@Override
	public int getActiveSessions() {
		return sessionIds.size();
	}
}