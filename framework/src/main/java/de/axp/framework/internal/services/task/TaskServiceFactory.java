package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

public final class TaskServiceFactory {

	public static TaskService createTaskService(MainLoop mainLoop, ServiceRegistry serviceRegistry,
	                                            PluginRegistry pluginRegistry) {
		return new TaskServiceImpl(mainLoop, serviceRegistry, pluginRegistry);
	}

	public static TaskService.Task createTask(String id, Object content) {
		return new TaskImpl(id, content);
	}

	public static TaskService.TaskResponse createTaskResponse(String id, Object content,
	                                                          TaskService.TaskResolution resolution) {
		return new TaskResponseImpl(id, content, resolution);
	}
}
