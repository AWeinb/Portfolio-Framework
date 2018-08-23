package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry;

public final class TaskServiceFactory {

	public static TaskService createTaskService(BaseServiceRegistry serviceRegistry, PluginRegistry pluginRegistry,
	                                            SessionService.FrameworkSession session) {
		return new TaskServiceImpl(serviceRegistry, pluginRegistry, session);
	}

	public static TaskService.Task createTask(String contextId, String taskId, Object content) {
		return new TaskImpl(contextId, taskId, content);
	}
}
