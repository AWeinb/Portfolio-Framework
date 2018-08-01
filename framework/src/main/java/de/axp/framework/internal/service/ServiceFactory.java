package de.axp.framework.internal.service;

import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.service.session.SessionServiceFactory;
import de.axp.framework.internal.service.session.InternalSessionService;
import de.axp.framework.internal.service.task.InternalTaskService;
import de.axp.framework.internal.service.task.TaskServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

	public static ServiceRegistry createServiceRegistry(MainLoop mainLoop) {
		Map<Class, InternalFrameworkService> internalFrameworkServices = new HashMap<>();
		internalFrameworkServices.put(InternalTaskService.class, TaskServiceFactory.createInternalTaskService(mainLoop));
		internalFrameworkServices.put(InternalSessionService.class, SessionServiceFactory.createInternalSessionService());
		return new ServiceRegistryImpl(internalFrameworkServices);
	}
}
