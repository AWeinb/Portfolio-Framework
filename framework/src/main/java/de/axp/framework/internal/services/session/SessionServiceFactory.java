package de.axp.framework.internal.services.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.services.BaseServiceRegistry;

public final class SessionServiceFactory {

	public static SessionService createSessionService(BaseServiceRegistry serviceRegistry,
	                                                  SessionService.FrameworkSession frameworkSession) {
		return new SessionServiceImpl(serviceRegistry, frameworkSession);
	}
}
