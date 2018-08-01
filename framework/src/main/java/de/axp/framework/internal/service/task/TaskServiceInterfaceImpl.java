package de.axp.framework.internal.service.task;

import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.framework.internal.service.ServiceRegistry;
import de.axp.framework.internal.service.session.SessionService;

class TaskServiceInterfaceImpl implements TaskServiceInterface {

	private ServiceRegistry serviceRegistry;
	private FrameworkSession session;

	TaskServiceInterfaceImpl(ServiceRegistry serviceRegistry, FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public void addTaskHandler(TaskHandler taskHandler) {
		addTaskHandler("", taskHandler);
	}

	@Override
	public void addTaskHandler(String contextId, TaskHandler taskHandler) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		TaskService taskService = (TaskService) serviceRegistry.get(TaskService.class);
		taskService.register(session.toString(), contextId, taskHandler);
	}

	@Override
	public void triggerTask(String taskId, Object content, TaskPromise promise) {
		triggerTask("", taskId, content, promise);
	}

	@Override
	public void triggerTask(String contextId, String taskId, Object content, TaskPromise promise) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		Task task = Task.build(contextId, taskId, content);
		TaskService taskService = (TaskService) serviceRegistry.get(TaskService.class);
		taskService.trigger(session.toString(), task, promise);
	}
}
