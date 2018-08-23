package de.axp.framework.internal.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.session.BaseSessionService;
import de.axp.framework.internal.services.session.SessionServiceFactory;
import de.axp.framework.internal.services.task.BaseTaskService;
import de.axp.framework.internal.services.task.TaskServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseServiceRegistry {

	private final Map<Class, BaseFrameworkService> serviceMap = new HashMap<>();

	public BaseServiceRegistry(MainLoop mainLoop) {
		serviceMap.put(BaseSessionService.class, new BaseSessionService());
		serviceMap.put(BaseTaskService.class, new BaseTaskService(mainLoop));
	}

	public <T extends BaseFrameworkService> T getBaseService(Class<T> serviceClass) {
		return (T) serviceMap.get(serviceClass);
	}

	public AuthenticatedServiceRegistry adaptToSession(SessionService.FrameworkSession session,
	                                                   BaseServiceRegistry serviceRegistry) {
		return new AuthenticatedServiceRegistry(session, serviceRegistry);
	}

	public class AuthenticatedServiceRegistry {

		private final Map<Class, FrameworkService> authenticatedServiceMap = new HashMap<>();

		AuthenticatedServiceRegistry(SessionService.FrameworkSession session, BaseServiceRegistry serviceRegistry) {
			PluginRegistry pluginRegistry = new PluginRegistry();

			SessionService sessionService = SessionServiceFactory.createSessionService(serviceRegistry, session);
			TaskService taskService = TaskServiceFactory.createTaskService(serviceRegistry, pluginRegistry, session);

			authenticatedServiceMap.put(SessionService.class, sessionService);
			authenticatedServiceMap.put(TaskService.class, taskService);
		}

		public <T extends FrameworkService> T getService(Class<T> serviceClass) {
			return (T) authenticatedServiceMap.get(serviceClass);
		}
	}
}
