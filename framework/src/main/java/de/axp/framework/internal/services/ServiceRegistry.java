package de.axp.framework.internal.services;

import java.util.HashMap;
import java.util.Map;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.data.DataServiceFactory;
import de.axp.framework.internal.services.plugin.PluginServiceFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;

public class ServiceRegistry {

	private final Map<Class<? extends FrameworkService>, FrameworkService> serviceMap = new HashMap<>();

	public ServiceRegistry(MainLoop mainLoop, PluginRegistry pluginRegistry) {
		serviceMap.put(PluginService.class, PluginServiceFactory.createPluginService(pluginRegistry));
		serviceMap.put(TaskService.class, TaskServiceFactory.createTaskService(mainLoop, this, pluginRegistry));
		serviceMap.put(DataService.class, DataServiceFactory.createDataService(mainLoop, this, pluginRegistry));
	}

	public <T extends FrameworkService> T getService(Class<T> serviceClass) {
		return (T) serviceMap.get(serviceClass);
	}
}
