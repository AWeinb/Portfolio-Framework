package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.BaseServiceRegistry;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final BaseServiceRegistry.AuthenticatedServiceRegistry serviceRegistry;

	PortfolioFrameworkImpl(BaseServiceRegistry.AuthenticatedServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public TaskService getTaskService() {
		return serviceRegistry.getService(TaskService.class);
	}

	@Override
	public DataService getDataService() {
		return serviceRegistry.getService(DataService.class);
	}
}
