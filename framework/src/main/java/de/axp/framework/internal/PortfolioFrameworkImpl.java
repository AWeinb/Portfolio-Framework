package de.axp.framework.internal;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TaskService;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final ServiceService serviceService;

	PortfolioFrameworkImpl(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@Override
	public <T extends FrameworkService> T getServiceByType(Class<T> type) {
		return serviceService.getService(type);
	}

	@Override
	public ServiceService getServiceService() {
		return serviceService.getService(ServiceService.class);
	}

	@Override
	public PluginService getPluginService() {
		return serviceService.getService(PluginService.class);
	}

	@Override
	public TaskService getTaskService() {
		return serviceService.getService(TaskService.class);
	}

	@Override
	public DataService getDataService() {
		return serviceService.getService(DataService.class);
	}

	@Override
	public void shutdown() {
		serviceService.disposeServices();
	}
}
