package de.axp.portfolio.plugin;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.extension.PortfolioFrameworkPlugIn;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;

public class TestPlugIn implements PortfolioFrameworkPlugIn {

	@Override
	public void initialize(AuthenticatedPortfolioFramework framework) {
		framework.getFrameworkTaskService()
				.addTaskHandler("Doge",
						(task, promise) -> promise.respond(TaskServiceInterface.TaskResolution.REJECTED, "Foo"));
	}
}
