package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

public final class TaskServiceFactory {

	private TaskServiceFactory() {
	}

	public static TaskService createTaskService(MainLoop mainLoop, ServiceRegistry serviceRegistry,
			PluginRegistry pluginRegistry) {
		return new TaskServiceImpl(mainLoop, serviceRegistry, pluginRegistry);
	}

	public static TaskService.Task createTask(String taskId, String handlerId, Object content) {
		return new TaskImpl(taskId, handlerId, content);
	}

	public static TaskService.TaskResponse createTaskResponse(String taskId, Object content,
			TaskService.TaskResolution resolution) {
		return new TaskResponseImpl(taskId, content, resolution);
	}
}
