package de.axp.framework.internal;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.api.ServiceManager;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TranslationService;
import de.axp.framework.internal.services.data.DataServiceFactory;
import de.axp.framework.internal.services.plugin.PluginServiceFactory;
import de.axp.framework.internal.services.ServiceManagerFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;
import de.axp.framework.internal.services.translation.TranslationServiceFactory;
import de.axp.framework.internal.services.portfolio.PortfolioServiceFactory;

public final class InternalFactory {

	private InternalFactory() {
	}

	public static PortfolioFrameworkImpl createFramework() {
		ServiceManager serviceManager = ServiceManagerFactory.createServiceManager();

		PluginService pluginService = PluginServiceFactory.createPluginService();
		TaskService taskService = TaskServiceFactory.createTaskService(serviceManager);
		DataService dataService = DataServiceFactory.createDataService(serviceManager);
		PortfolioService portfolioService = PortfolioServiceFactory.createPortfolioService(serviceManager);
		TranslationService translationService = TranslationServiceFactory.createTranslationService(serviceManager);

		serviceManager.registerNewService(PluginService.class, pluginService);
		serviceManager.registerNewService(TaskService.class, taskService);
		serviceManager.registerNewService(DataService.class, dataService);
		serviceManager.registerNewService(PortfolioService.class, portfolioService);
		serviceManager.registerNewService(TranslationService.class, translationService);

		return new PortfolioFrameworkImpl(serviceManager);
	}
}
