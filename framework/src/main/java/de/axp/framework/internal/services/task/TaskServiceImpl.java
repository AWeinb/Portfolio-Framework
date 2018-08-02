package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.ServiceRegistry;
import de.axp.framework.internal.services.session.InternalSessionService;

class TaskServiceImpl implements TaskService {

	private ServiceRegistry serviceRegistry;
	private SessionService.FrameworkSession session;

	TaskServiceImpl(ServiceRegistry serviceRegistry, SessionService.FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public void addTaskHandler(TaskHandler taskHandler) {
		addTaskHandler("", taskHandler);
	}

	@Override
	public void addTaskHandler(String contextId, TaskHandler taskHandler) {
		InternalSessionService internalSessionService = (InternalSessionService) serviceRegistry.get(
				InternalSessionService.class);
		internalSessionService.checkSession(session);

		InternalTaskService internalTaskService = (InternalTaskService) serviceRegistry.get(InternalTaskService.class);
		internalTaskService.register(session.toString(), contextId, taskHandler);
	}

	@Override
	public void triggerTask(String taskId, Object content, TaskPromise promise) {
		triggerTask("", taskId, content, promise);
	}

	@Override
	public void triggerTask(String contextId, String taskId, Object content, TaskPromise promise) {
		InternalSessionService internalSessionService = (InternalSessionService) serviceRegistry.get(
				InternalSessionService.class);
		internalSessionService.checkSession(session);

		Task task = Task.build(contextId, taskId, content);
		InternalTaskService internalTaskService = (InternalTaskService) serviceRegistry.get(InternalTaskService.class);
		internalTaskService.trigger(session.toString(), task, promise);
	}
}
