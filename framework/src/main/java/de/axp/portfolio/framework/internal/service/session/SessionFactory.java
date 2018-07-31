package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

public class SessionFactory {

	public static SessionService createSessionService() {
		return new SessionServiceImpl();
	}

	public static SessionServiceInterface createSessionServiceInterface(ServiceRegistry serviceRegistry,
	                                                                    FrameworkSession frameworkSession) {
		return new SessionServiceInterfaceImpl(serviceRegistry, frameworkSession);
	}
}
