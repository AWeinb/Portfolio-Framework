package de.axp.framework.internal;

import de.axp.framework.api.FrameworkThreadSynchronizer;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.BaseServiceRegistry;
import de.axp.framework.internal.services.session.BaseSessionService;
import de.axp.framework.internal.services.task.BaseTaskService;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final BaseServiceRegistry serviceRegistry;
	private final SessionService.FrameworkSession session;
	private final SessionService sessionService;
	private final TaskService taskService;

	PortfolioFrameworkImpl(BaseServiceRegistry serviceRegistry, SessionService.FrameworkSession session,
	                       SessionService sessionService, TaskService taskService) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
		this.sessionService = sessionService;
		this.taskService = taskService;
	}

	@Override
	public void setMainThreadSynchronization(FrameworkThreadSynchronizer synchronization) {
		BaseSessionService internalSessionService = serviceRegistry.getBaseService(BaseSessionService.class);
		internalSessionService.checkSession(session);

		BaseTaskService internalTaskService = serviceRegistry.getBaseService(BaseTaskService.class);
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
