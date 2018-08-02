package de.axp.framework.internal.services;

import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.services.session.InternalSessionService;
import de.axp.framework.internal.services.session.SessionServiceFactory;
import de.axp.framework.internal.services.task.InternalTaskService;
import de.axp.framework.internal.services.task.TaskServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {

	private final Map<Class, InternalFrameworkService> serviceMap = new HashMap<>();

	public ServiceRegistry(MainLoop mainLoop) {
		serviceMap.put(InternalTaskService.class, TaskServiceFactory.createInternalTaskService(mainLoop));
		serviceMap.put(InternalSessionService.class, SessionServiceFactory.createInternalSessionService());
	}

	public InternalFrameworkService get(Class serviceClass) {
		return serviceMap.get(serviceClass);
	}
}
