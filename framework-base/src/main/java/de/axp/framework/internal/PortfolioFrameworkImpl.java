package de.axp.framework.internal;

import de.axp.framework.api.FrameworkExtension;
import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.api.ServiceManager;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TranslationService;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final ServiceManager serviceManager;

	PortfolioFrameworkImpl(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	@Override
	public <T extends FrameworkService> T getServiceByType(Class<T> type) {
		return serviceManager.getService(type);
	}

	@Override
	public PluginService getPluginService() {
		return serviceManager.getService(PluginService.class);
	}

	@Override
	public TaskService getTaskService() {
		return serviceManager.getService(TaskService.class);
	}

	@Override
	public DataService getDataService() {
		return serviceManager.getService(DataService.class);
	}

	@Override
	public PortfolioService getPortfolioService() {
		return serviceManager.getService(PortfolioService.class);
	}

	@Override
	public TranslationService getTranslationService() {
		return serviceManager.getService(TranslationService.class);
	}

	@Override
	public void shutdown() {
		serviceManager.disposeServices();
	}

	@Override
	public void install(FrameworkExtension frameworkExtension) {
		frameworkExtension.install(() -> this);
	}
}
