package de.axp.framework.internal;

import de.axp.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.framework.internal.service.ServiceRegistry;
import de.axp.framework.internal.service.session.SessionService;
import de.axp.framework.internal.service.task.TaskService;
import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.MainThreadSynchronization;

class AuthenticatedPortfolioFrameworkImpl implements AuthenticatedPortfolioFramework {

	private final ServiceRegistry serviceRegistry;
	private final FrameworkSession session;
	private final SessionServiceInterface sessionServiceInterface;
	private final TaskServiceInterface taskServiceInterface;

	AuthenticatedPortfolioFrameworkImpl(ServiceRegistry serviceRegistry, FrameworkSession session,
	                                    SessionServiceInterface sessionServiceInterface,
	                                    TaskServiceInterface taskServiceInterface) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
		this.sessionServiceInterface = sessionServiceInterface;
		this.taskServiceInterface = taskServiceInterface;
	}

	@Override
	public void setMainThreadSynchronization(MainThreadSynchronization synchronization) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		TaskService taskService = (TaskService) serviceRegistry.get(TaskService.class);
		taskService.setMainThreadSynchronization(synchronization);
	}

	@Override
	public SessionServiceInterface getFrameworkSessionService() {
		return sessionServiceInterface;
	}

	@Override
	public TaskServiceInterface getFrameworkTaskService() {
		return taskServiceInterface;
	}
}
