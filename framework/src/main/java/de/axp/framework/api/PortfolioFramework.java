package de.axp.framework.api;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.InternalFactory;

public interface PortfolioFramework {

	static PortfolioFramework createFramework() {
		return InternalFactory.createFramework();
	}

	TaskService getTaskService();

	DataService getDataService();

	void shutdown();
}
