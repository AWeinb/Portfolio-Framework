package de.axp.framework.internal.service;

import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.service.session.SessionFactory;
import de.axp.framework.internal.service.session.SessionService;
import de.axp.framework.internal.service.task.TaskService;
import de.axp.framework.internal.service.task.TaskServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

	public static ServiceRegistry createServiceRegistry(MainLoop mainLoop) {
		Map<Class, InternalFrameworkService> internalFrameworkServices = new HashMap<>();
		internalFrameworkServices.put(TaskService.class, TaskServiceFactory.createTaskService(mainLoop));
		internalFrameworkServices.put(SessionService.class, SessionFactory.createSessionService());
		return new ServiceRegistryImpl(internalFrameworkServices);
	}
}
