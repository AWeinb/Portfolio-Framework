package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.MainThreadSynchronization;
import de.axp.portfolio.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.session.SessionService;
import de.axp.portfolio.framework.internal.service.task.TaskService;

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
