package de.axp.framework.internal.service.interfaces;

import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.service.ServiceRegistry;

public final class InterfaceFactory {

	public static SessionService createSessionServiceInterface(ServiceRegistry serviceRegistry,
	                                                           FrameworkSession frameworkSession) {
		return new SessionServiceImpl(serviceRegistry, frameworkSession);
	}

	public static TaskService createTaskServiceInterface(ServiceRegistry serviceRegistry,
	                                                     FrameworkSession session) {
		return new TaskServiceImpl(serviceRegistry, session);
	}
}
