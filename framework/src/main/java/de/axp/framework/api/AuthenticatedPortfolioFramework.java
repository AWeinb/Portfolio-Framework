package de.axp.framework.api;

import de.axp.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;

public interface AuthenticatedPortfolioFramework {

	void setMainThreadSynchronization(MainThreadSynchronization synchronization);

	SessionServiceInterface getFrameworkSessionService();

	TaskServiceInterface getFrameworkTaskService();

}
