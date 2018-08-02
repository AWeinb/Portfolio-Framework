package de.axp.framework.internal.services.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.services.InternalFrameworkService;
import de.axp.framework.internal.services.ServiceRegistry;

public final class SessionServiceFactory {

	public static SessionService createSessionService(ServiceRegistry serviceRegistry,
	                                                  SessionService.FrameworkSession frameworkSession) {
		return new SessionServiceImpl(serviceRegistry, frameworkSession);
	}

	public static InternalFrameworkService createInternalSessionService() {
		return new InternalSessionServiceImpl();
	}
}
