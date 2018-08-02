package de.axp.framework.internal.services.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.services.InternalFrameworkService;

public interface InternalSessionService extends InternalFrameworkService {

	SessionService.FrameworkSession initializeSession(Authentication authentication);

	void invalidateSession(SessionService.FrameworkSession session);

	void checkSession(SessionService.FrameworkSession session);

	int getActiveSessions();
}
