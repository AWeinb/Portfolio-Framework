package de.axp.framework.internal.service.session;

import de.axp.framework.api.FrameworkSession;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.service.InternalFrameworkService;

public interface InternalSessionService extends InternalFrameworkService {

	FrameworkSession initializeSession(Authentication authentication);

	void invalidateSession(FrameworkSession session);

	void checkSession(FrameworkSession session);

	int getActiveSessions();
}
