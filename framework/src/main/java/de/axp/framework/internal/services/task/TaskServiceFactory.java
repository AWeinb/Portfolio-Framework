package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.services.InternalFrameworkService;
import de.axp.framework.internal.services.ServiceRegistry;

public final class TaskServiceFactory {

	public static TaskService createTaskService(ServiceRegistry serviceRegistry,
	                                            SessionService.FrameworkSession session) {
		return new TaskServiceImpl(serviceRegistry, session);
	}

	public static InternalFrameworkService createInternalTaskService(MainLoop mainLoop) {
		InternalTaskServiceImpl internalTaskService = new InternalTaskServiceImpl();
		mainLoop.addPlugin(internalTaskService);
		return internalTaskService;
	}

	public static TaskService.Task createTask(String contextId, String taskId, Object content) {
		return new TaskImpl(contextId, taskId, content);
	}
}
