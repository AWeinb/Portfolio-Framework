package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.UserSessionAccessor;
import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.event.Task;
import de.axp.portfolio.framework.internal.service.event.TaskService;
import de.axp.portfolio.framework.internal.service.session.SessionService;

class AuthenticatedFrameworkImpl implements AuthenticatedFramework, TaskServiceInterface {

	private final ServiceRegistry serviceRegistry;
	private final FrameworkSession session;

	AuthenticatedFrameworkImpl(ServiceRegistry serviceRegistry, FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public FrameworkSession getSession() {
		return session;
	}

	@Override
	public void setUserSessionAccessor(UserSessionAccessor accessor) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		TaskService taskService = (TaskService) serviceRegistry.get(TaskService.class);
		taskService.setUserSessionAccessor(accessor);
	}

	@Override
	public TaskServiceInterface getFrameworkTaskService() {
		return this;
	}

	@Override
	public void invalidate(FrameworkSession session) {
		InternalFrameworkService internalFrameworkService = serviceRegistry.get(SessionService.class);
		SessionService sessionService = (SessionService) internalFrameworkService;
		sessionService.invalidateSession(session);
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

		Task task = Task.build(taskId, content);
		TaskService taskService = (TaskService) serviceRegistry.get(TaskService.class);
		taskService.trigger(session.toString(), contextId, task, promise);
	}
}
