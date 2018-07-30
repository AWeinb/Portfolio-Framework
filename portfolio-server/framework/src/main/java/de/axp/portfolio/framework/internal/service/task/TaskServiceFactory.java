package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

public class TaskServiceFactory {

	public static TaskService createTaskService(MainLoop mainLoop) {
		TaskServiceImpl taskService = new TaskServiceImpl();
		mainLoop.addPlugin(taskService);
		return taskService;
	}

	public static TaskServiceInterface createTaskServiceInterface(ServiceRegistry serviceRegistry,
	                                                              FrameworkSession session) {
		return new TaskServiceInterfaceImpl(serviceRegistry, session);
	}
}
