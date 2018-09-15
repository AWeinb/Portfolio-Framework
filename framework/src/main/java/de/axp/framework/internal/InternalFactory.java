package de.axp.framework.internal;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.data.DataServiceFactory;
import de.axp.framework.internal.services.plugin.PluginServiceFactory;
import de.axp.framework.internal.services.service.ServiceRegistry;
import de.axp.framework.internal.services.service.ServiceServiceFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;

public final class InternalFactory {

	private InternalFactory() {
	}

	public static PortfolioFrameworkImpl createFramework() {
		ServiceRegistry serviceRegistry = new ServiceRegistry();

		ServiceService serviceService = ServiceServiceFactory.createServiceService(serviceRegistry);
		PluginService pluginService = PluginServiceFactory.createPluginService();
		TaskService taskService = TaskServiceFactory.createTaskService(serviceRegistry);
		DataService dataService = DataServiceFactory.createDataService(serviceRegistry);

		serviceRegistry.putService(ServiceService.class, serviceService);
		serviceRegistry.putService(PluginService.class, pluginService);
		serviceRegistry.putService(TaskService.class, taskService);
		serviceRegistry.putService(DataService.class, dataService);

		return new PortfolioFrameworkImpl(serviceRegistry);
	}
}
