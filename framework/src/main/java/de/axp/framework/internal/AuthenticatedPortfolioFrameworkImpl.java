package de.axp.framework.internal;

import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.MainThreadSynchronization;
import de.axp.framework.api.services.SessionServiceInterface;
import de.axp.framework.api.services.TaskServiceInterface;
import de.axp.framework.internal.service.ServiceRegistry;
import de.axp.framework.internal.service.session.InternalSessionService;
import de.axp.framework.internal.service.task.InternalTaskService;

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
		InternalSessionService internalSessionService = (InternalSessionService) serviceRegistry.get(
				InternalSessionService.class);
		internalSessionService.checkSession(session);

		InternalTaskService internalTaskService = (InternalTaskService) serviceRegistry.get(InternalTaskService.class);
		internalTaskService.setMainThreadSynchronization(synchronization);
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
