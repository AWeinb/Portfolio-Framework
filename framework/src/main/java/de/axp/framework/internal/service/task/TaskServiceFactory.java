package de.axp.framework.internal.service.task;

import de.axp.framework.api.services.TaskServiceInterface;
import de.axp.framework.internal.mainloop.MainLoop;

public final class TaskServiceFactory {

	public static TaskService createTaskService(MainLoop mainLoop) {
		TaskServiceImpl taskService = new TaskServiceImpl();
		mainLoop.addPlugin(taskService);
		return taskService;
	}

	public static TaskServiceInterface.Task createTask(String contextId, String taskId, Object content) {
		return new TaskImpl(contextId, taskId, content);
	}
}
