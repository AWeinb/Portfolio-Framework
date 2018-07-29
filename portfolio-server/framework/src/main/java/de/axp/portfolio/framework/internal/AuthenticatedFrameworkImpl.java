package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
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
	public TaskServiceInterface getFrameworkEventInterface() {
		return this;
	}

	@Override
	public void invalidate(FrameworkSession session) {
		InternalFrameworkService internalFrameworkService = serviceRegistry.get(SessionService.class);
		SessionService sessionService = (SessionService) internalFrameworkService;
		sessionService.invalidateSession(session);
	}

	@Override
	public void addHandler(TaskHandler taskHandler) {
		addHandler("", taskHandler);
	}

	@Override
	public void addHandler(String context, TaskHandler taskHandler) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		TaskService listenerService = (TaskService) serviceRegistry.get(TaskService.class);
		listenerService.register(session.toString(), context, taskHandler);
	}

	@Override
	public void triggerTask(String eventID, Object content, TaskPromise promise) {
		triggerTask("", eventID, content, promise);
	}

	@Override
	public void triggerTask(String context, String eventID, Object content, TaskPromise promise) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		Task event = Task.build(eventID, content);
		TaskService eventService = (TaskService) serviceRegistry.get(TaskService.class);
		eventService.trigger(session.toString(), context, event, promise);
	}
}
