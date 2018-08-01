package de.axp.framework.internal.service.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.service.InternalFrameworkService;
import de.axp.framework.internal.service.ServiceRegistry;

public final class SessionServiceFactory {

	public static SessionService createSessionService(ServiceRegistry serviceRegistry,
	                                                  SessionService.FrameworkSession frameworkSession) {
		return new SessionServiceImpl(serviceRegistry, frameworkSession);
	}

	public static InternalFrameworkService createInternalSessionService() {
		return new InternalSessionServiceImpl();
	}
}
