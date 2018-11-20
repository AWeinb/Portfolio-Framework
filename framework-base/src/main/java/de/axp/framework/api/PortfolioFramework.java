package de.axp.framework.api;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TranslationService;
import de.axp.framework.internal.InternalFactory;

public interface PortfolioFramework {

	static PortfolioFramework createFramework() {
		return InternalFactory.createFramework();
	}

	ServiceManager getServiceManager();

	<T extends FrameworkService> T getServiceByType(Class<T> type);

	PluginService getPluginService();

	TaskService getTaskService();

	DataService getDataService();

	PortfolioService getPortfolioService();

	TranslationService getTranslationService();

	void shutdown();

	void install(FrameworkExtension frameworkExtension);
}
