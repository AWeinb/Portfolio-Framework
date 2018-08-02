package de.axp.framework.internal.services.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.services.BaseFrameworkService;

import java.util.HashSet;
import java.util.Set;

public class BaseSessionService implements BaseFrameworkService {

	private final Set<String> sessionIds = new HashSet<>();

	public SessionService.FrameworkSession initializeSession(Authentication authentication) {
		if (authentication == null) {
			throw new IllegalArgumentException();
		}
		return new SessionService.FrameworkSession() {
		};
	}

	public void invalidateSession(SessionService.FrameworkSession session) {
	}

	public void checkSession(SessionService.FrameworkSession session) {
	}

	public int getActiveSessions() {
		return sessionIds.size();
	}
}