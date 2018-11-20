package de.axp.framework.internal;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TranslationService;
import de.axp.framework.internal.services.data.DataServiceFactory;
import de.axp.framework.internal.services.plugin.PluginServiceFactory;
import de.axp.framework.internal.services.service.ServiceServiceFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;
import de.axp.framework.internal.services.translation.TranslationServiceFactory;
import de.axp.framework.internal.services.portfolio.PortfolioServiceFactory;

public final class InternalFactory {

	private InternalFactory() {
	}

	public static PortfolioFrameworkImpl createFramework() {
		ServiceService serviceService = ServiceServiceFactory.createServiceService();
		PluginService pluginService = PluginServiceFactory.createPluginService();
		TaskService taskService = TaskServiceFactory.createTaskService(serviceService);
		DataService dataService = DataServiceFactory.createDataService(serviceService);
		PortfolioService portfolioService = PortfolioServiceFactory.createPortfolioService(serviceService);
		TranslationService translationService = TranslationServiceFactory.createTranslationService(serviceService);

		serviceService.registerNewService(ServiceService.class, serviceService);
		serviceService.registerNewService(PluginService.class, pluginService);
		serviceService.registerNewService(TaskService.class, taskService);
		serviceService.registerNewService(DataService.class, dataService);
		serviceService.registerNewService(PortfolioService.class, portfolioService);
		serviceService.registerNewService(TranslationService.class, translationService);

		return new PortfolioFrameworkImpl(serviceService);
	}
}
