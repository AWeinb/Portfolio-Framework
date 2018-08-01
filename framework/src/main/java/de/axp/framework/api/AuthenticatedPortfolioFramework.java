package de.axp.framework.api;

import de.axp.framework.api.services.SessionServiceInterface;
import de.axp.framework.api.services.TaskServiceInterface;

public interface AuthenticatedPortfolioFramework {

	void setMainThreadSynchronization(MainThreadSynchronization synchronization);

	SessionServiceInterface getFrameworkSessionService();

	TaskServiceInterface getFrameworkTaskService();

}
