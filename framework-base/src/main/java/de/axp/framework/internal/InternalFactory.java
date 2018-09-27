package de.axp.framework.internal;

import de.axp.framework.api.services.*;
import de.axp.framework.internal.services.data.DataServiceFactory;
import de.axp.framework.internal.services.plugin.PluginServiceFactory;
import de.axp.framework.internal.services.service.ServiceServiceFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;
import de.axp.framework.internal.services.ui.UiServiceFactory;

public final class InternalFactory {

	private InternalFactory() {
	}

	public static PortfolioFrameworkImpl createFramework() {
		ServiceService serviceService = ServiceServiceFactory.createServiceService();
		PluginService pluginService = PluginServiceFactory.createPluginService();
		TaskService taskService = TaskServiceFactory.createTaskService(serviceService);
		DataService dataService = DataServiceFactory.createDataService(serviceService);
		UiService uiService = UiServiceFactory.createUiService(serviceService);

		serviceService.registerNewService(ServiceService.class, serviceService);
		serviceService.registerNewService(PluginService.class, pluginService);
		serviceService.registerNewService(TaskService.class, taskService);
		serviceService.registerNewService(DataService.class, dataService);
		serviceService.registerNewService(UiService.class, uiService);

		return new PortfolioFrameworkImpl(serviceService);
	}
}
