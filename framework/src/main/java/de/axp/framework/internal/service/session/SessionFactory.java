package de.axp.framework.internal.service.session;

import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.framework.internal.service.ServiceRegistry;

public class SessionFactory {

	public static SessionService createSessionService() {
		return new SessionServiceImpl();
	}

	public static SessionServiceInterface createSessionServiceInterface(ServiceRegistry serviceRegistry,
	                                                                    FrameworkSession frameworkSession) {
		return new SessionServiceInterfaceImpl(serviceRegistry, frameworkSession);
	}
}
