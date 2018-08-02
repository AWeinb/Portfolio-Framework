package de.axp.framework.api;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;

public interface PortfolioFramework {

	void setMainThreadSynchronization(FrameworkThreadSynchronizer synchronization);

	SessionService getFrameworkSessionService();

	TaskService getFrameworkTaskService();

}
