package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;

public interface AuthenticatedPortfolioFramework {

	void setMainThreadSynchronization(MainThreadSynchronization synchronization);

	SessionServiceInterface getFrameworkSessionService();

	TaskServiceInterface getFrameworkTaskService();

}
