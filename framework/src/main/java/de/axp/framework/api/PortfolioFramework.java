package de.axp.framework.api;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.InternalFactory;

public interface PortfolioFramework {

	static BasePortfolioFramework createBaseFramework() {
		return InternalFactory.createFramework();
	}

	void setMainThreadSynchronization(FrameworkThreadSynchronizer synchronization);

	SessionService getFrameworkSessionService();

	TaskService getFrameworkTaskService();

	interface BasePortfolioFramework {

		PortfolioFramework adaptToUser(String user);

		void shutdown();

	}
}
