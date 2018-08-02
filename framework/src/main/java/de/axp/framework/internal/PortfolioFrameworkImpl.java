package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.BaseServiceRegistry;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final BaseServiceRegistry.AuthenticatedServiceRegistry serviceRegistry;

	PortfolioFrameworkImpl(BaseServiceRegistry.AuthenticatedServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public SessionService getSessionService() {
		SessionService sessionService = serviceRegistry.getService(SessionService.class);
		sessionService.validate();

		return sessionService;
	}

	@Override
	public TaskService getTaskService() {
		SessionService sessionService = serviceRegistry.getService(SessionService.class);
		sessionService.validate();

		return serviceRegistry.getService(TaskService.class);
	}
}
