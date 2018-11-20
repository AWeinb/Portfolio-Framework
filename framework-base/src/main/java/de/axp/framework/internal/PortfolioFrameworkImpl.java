package de.axp.framework.internal;

import de.axp.framework.api.FrameworkExtension;
import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.*;

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
	public PortfolioService getPortfolioService() {
		return serviceService.getService(PortfolioService.class);
	}

	@Override
	public TranslationService getTranslationService() {
		return serviceService.getService(TranslationService.class);
	}

	@Override
	public void shutdown() {
		serviceService.disposeServices();
	}

	@Override
	public void install(FrameworkExtension frameworkExtension) {
		frameworkExtension.install(() -> this);
	}
}
