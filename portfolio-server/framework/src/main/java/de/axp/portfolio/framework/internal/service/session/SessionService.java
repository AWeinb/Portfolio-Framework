package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.internal.service.FrameworkService;

public interface SessionService extends FrameworkService {

	void initializeSession(String sessionID);

	void checkID(String sessionID);

	int getActiveSessions();

	void disposeSession(String sessionID);
}
