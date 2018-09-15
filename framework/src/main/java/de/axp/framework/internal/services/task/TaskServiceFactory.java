package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TaskService;

public final class TaskServiceFactory {

	private TaskServiceFactory() {
	}

	public static TaskService createTaskService(ServiceService serviceService) {
		return new TaskServiceImpl(serviceService);
	}

	public static TaskService.Task createTask(String taskId, String handlerId, Object content) {
		return new TaskImpl(taskId, handlerId, content);
	}

	public static TaskService.TaskResponse createTaskResponse(String taskId, Object content,
	                                                          TaskService.TaskResolution resolution) {
		return new TaskResponseImpl(taskId, content, resolution);
	}
}
