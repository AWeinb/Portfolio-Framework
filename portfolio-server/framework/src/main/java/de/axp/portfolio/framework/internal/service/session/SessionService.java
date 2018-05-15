package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface SessionService extends InternalFrameworkService {

	void initializeSession(String sessionId);

	void checkID(String sessionId);

	int getActiveSessions();

	void disposeSession(String sessionId);
}
