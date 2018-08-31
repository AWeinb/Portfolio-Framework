package de.axp.framework.internal.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.PortfolioFramework.FrameworkSession;
import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.data.BaseDataService;
import de.axp.framework.internal.services.data.DataServiceFactory;
import de.axp.framework.internal.services.task.BaseTaskService;
import de.axp.framework.internal.services.task.TaskServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseServiceRegistry {

	private final Map<Class, BaseFrameworkService> serviceMap = new HashMap<>();

	public BaseServiceRegistry(MainLoop mainLoop) {
		serviceMap.put(BaseTaskService.class, new BaseTaskService(mainLoop));
		serviceMap.put(BaseDataService.class, new BaseDataService(mainLoop));
	}

	public <T extends BaseFrameworkService> T getBaseService(Class<T> serviceClass) {
		return (T) serviceMap.get(serviceClass);
	}

	public AuthenticatedServiceRegistry adaptToSession(FrameworkSession session, BaseServiceRegistry serviceRegistry) {
		return new AuthenticatedServiceRegistry(session, serviceRegistry);
	}

	public class AuthenticatedServiceRegistry {

		private final Map<Class, FrameworkService> authenticatedServiceMap = new HashMap<>();

		AuthenticatedServiceRegistry(FrameworkSession session, BaseServiceRegistry serviceRegistry) {
			PluginRegistry pluginRegistry = new PluginRegistry();

			TaskService taskService = TaskServiceFactory.createTaskService(serviceRegistry, pluginRegistry, session);
			DataService dataService = DataServiceFactory.createDataService(serviceRegistry, pluginRegistry, session);

			authenticatedServiceMap.put(TaskService.class, taskService);
			authenticatedServiceMap.put(DataService.class, dataService);
		}

		public <T extends FrameworkService> T getService(Class<T> serviceClass) {
			return (T) authenticatedServiceMap.get(serviceClass);
		}
	}
}
