package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.internal.authentication.Authentication;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface SessionService extends InternalFrameworkService {

	FrameworkSession initializeSession(Authentication authentication);

	void invalidateSession(FrameworkSession session);

	void checkSession(FrameworkSession session);

	int getActiveSessions();
}
