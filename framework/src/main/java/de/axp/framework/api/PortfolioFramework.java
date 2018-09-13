package de.axp.framework.api;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.InternalFactory;

public interface PortfolioFramework {

	static PortfolioFramework createFramework() {
		return InternalFactory.createFramework();
	}

	PluginService getPluginService();

	TaskService getTaskService();

	DataService getDataService();

	void shutdown();
}
