package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface SessionService extends InternalFrameworkService {

	void initializeSession(String sessionID);

	void checkID(String sessionID);

	int getActiveSessions();

	void disposeSession(String sessionID);
}
