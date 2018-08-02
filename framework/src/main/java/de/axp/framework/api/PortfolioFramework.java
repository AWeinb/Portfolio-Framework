package de.axp.framework.api;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.InternalFactory;
import de.axp.framework.internal.authentication.Authentication;

public interface PortfolioFramework {

	static BasePortfolioFramework createBaseFramework() {
		return InternalFactory.createFramework();
	}

	void setMainThreadSynchronization(FrameworkThreadSynchronizer synchronization);

	SessionService getFrameworkSessionService();

	TaskService getFrameworkTaskService();

	interface BasePortfolioFramework {

		PortfolioFramework adaptToAuthentication(Authentication authentication);

		void shutdown();

	}
}
