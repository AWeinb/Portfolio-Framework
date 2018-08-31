package de.axp.framework.api;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.InternalFactory;
import de.axp.framework.internal.authentication.Authentication;

public interface PortfolioFramework {

	static BasePortfolioFramework createBaseFramework() {
		return InternalFactory.createFramework();
	}

	TaskService getTaskService();

	interface FrameworkSession {

	}

	interface BasePortfolioFramework {

		PortfolioFramework adaptToUser(Authentication authentication);

		void shutdown();

	}
}
