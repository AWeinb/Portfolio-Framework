package de.axp.framework.internal.services.task;

import de.axp.framework.api.extensions.TaskHandler;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.BaseServiceRegistry;
import de.axp.framework.internal.services.session.BaseSessionService;

class TaskServiceImpl implements TaskService {

	private BaseServiceRegistry serviceRegistry;
	private SessionService.FrameworkSession session;

	TaskServiceImpl(BaseServiceRegistry serviceRegistry, SessionService.FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public void addTaskHandler(TaskHandler taskHandler) {
		addTaskHandler("", taskHandler);
	}

	@Override
	public void addTaskHandler(String contextId, TaskHandler taskHandler) {
		BaseSessionService internalSessionService = serviceRegistry.getBaseService(BaseSessionService.class);
		internalSessionService.checkSession(session);

		BaseTaskService internalTaskService = serviceRegistry.getBaseService(BaseTaskService.class);
		internalTaskService.register(session.toString(), contextId, taskHandler);
	}

	@Override
	public void triggerTask(String taskId, Object content, TaskPromise promise) {
		triggerTask("", taskId, content, promise);
	}

	@Override
	public void triggerTask(String contextId, String taskId, Object content, TaskPromise promise) {
		BaseSessionService internalSessionService = serviceRegistry.getBaseService(BaseSessionService.class);
		internalSessionService.checkSession(session);

		Task task = Task.build(contextId, taskId, content);
		BaseTaskService internalTaskService = serviceRegistry.getBaseService(BaseTaskService.class);
		internalTaskService.trigger(session.toString(), task, promise);
	}
}
