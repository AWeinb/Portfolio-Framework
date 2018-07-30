package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;

public interface AuthenticatedPortfolioFramework {

	FrameworkSession getSession();

	void setMainThreadSynchronization(MainThreadSynchronization synchronization);

	TaskServiceInterface getFrameworkTaskService();

	void invalidate(FrameworkSession session);

}
