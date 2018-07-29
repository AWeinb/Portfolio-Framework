package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class TaskServiceFactory {

	public static TaskService createTaskService(MainLoop mainLoop) {
		TaskServiceImpl taskService = new TaskServiceImpl();
		mainLoop.addPlugin(taskService);
		return taskService;
	}
}
