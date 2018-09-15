package de.axp.framework.internal;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.service.ServiceRegistry;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final ServiceRegistry serviceRegistry;

	PortfolioFrameworkImpl(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public <T extends FrameworkService> T getServiceByType(Class<T> type) {
		return serviceRegistry.getService(type);
	}

	@Override
	public ServiceService getServiceService() {
		return serviceRegistry.getService(ServiceService.class);
	}

	@Override
	public PluginService getPluginService() {
		return serviceRegistry.getService(PluginService.class);
	}

	@Override
	public TaskService getTaskService() {
		return serviceRegistry.getService(TaskService.class);
	}

	@Override
	public DataService getDataService() {
		return serviceRegistry.getService(DataService.class);
	}

	@Override
	public void shutdown() {
		serviceRegistry.disposeAll();
	}
}
