package de.axp.framework.internal.service.task;

import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.framework.internal.service.ServiceRegistry;

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

	public static TaskServiceInterface.Task createTask(String taskId, Object content) {
		return new TaskImpl(taskId, content);
	}
}
