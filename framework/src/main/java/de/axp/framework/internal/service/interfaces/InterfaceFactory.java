package de.axp.framework.internal.service.interfaces;

import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.services.SessionServiceInterface;
import de.axp.framework.api.services.TaskServiceInterface;
import de.axp.framework.internal.service.ServiceRegistry;

public final class InterfaceFactory {

	public static SessionServiceInterface createSessionServiceInterface(ServiceRegistry serviceRegistry,
	                                                                    FrameworkSession frameworkSession) {
		return new SessionServiceInterfaceImpl(serviceRegistry, frameworkSession);
	}

	public static TaskServiceInterface createTaskServiceInterface(ServiceRegistry serviceRegistry,
	                                                              FrameworkSession session) {
		return new TaskServiceInterfaceImpl(serviceRegistry, session);
	}
}
