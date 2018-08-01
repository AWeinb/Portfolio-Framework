package de.axp.framework.api;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;

public interface AuthenticatedPortfolioFramework {

	void setMainThreadSynchronization(MainThreadSynchronization synchronization);

	SessionService getFrameworkSessionService();

	TaskService getFrameworkTaskService();

}
