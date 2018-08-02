package de.axp.framework.internal.services;

import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.services.session.BaseSessionService;
import de.axp.framework.internal.services.session.SessionServiceFactory;
import de.axp.framework.internal.services.task.BaseTaskService;
import de.axp.framework.internal.services.task.TaskServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseServiceRegistry {

	private final Map<Class, BaseFrameworkService> serviceMap = new HashMap<>();

	public BaseServiceRegistry(MainLoop mainLoop) {
		serviceMap.put(BaseSessionService.class, SessionServiceFactory.createBaseSessionService());
		serviceMap.put(BaseTaskService.class, TaskServiceFactory.createBaseTaskService(mainLoop));
	}

	public <T extends BaseFrameworkService> T getBaseService(Class<T> serviceClass) {
		return (T) serviceMap.get(serviceClass);
	}
}
