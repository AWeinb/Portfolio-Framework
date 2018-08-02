package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.FrameworkThreadSynchronizer;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.ServiceRegistry;
import de.axp.framework.internal.services.session.InternalSessionService;
import de.axp.framework.internal.services.task.InternalTaskService;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final ServiceRegistry serviceRegistry;
	private final SessionService.FrameworkSession session;
	private final SessionService sessionService;
	private final TaskService taskService;

	PortfolioFrameworkImpl(ServiceRegistry serviceRegistry, SessionService.FrameworkSession session,
	                       SessionService sessionService, TaskService taskService) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
		this.sessionService = sessionService;
		this.taskService = taskService;
	}

	@Override
	public void setMainThreadSynchronization(FrameworkThreadSynchronizer synchronization) {
		InternalSessionService internalSessionService = (InternalSessionService) serviceRegistry.get(
				InternalSessionService.class);
		internalSessionService.checkSession(session);

		InternalTaskService internalTaskService = (InternalTaskService) serviceRegistry.get(InternalTaskService.class);
		internalTaskService.setMainThreadSynchronization(synchronization);
	}

	@Override
	public SessionService getFrameworkSessionService() {
		return sessionService;
	}

	@Override
	public TaskService getFrameworkTaskService() {
		return taskService;
	}
}
