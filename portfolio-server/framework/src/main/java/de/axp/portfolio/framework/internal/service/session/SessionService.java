package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.FrameworkAuthentication;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface SessionService extends InternalFrameworkService {

	FrameworkSession initializeSession(FrameworkAuthentication authentication);

	void checkID(String sessionId);

	int getActiveSessions();

	void disposeSession(String sessionId);
}
