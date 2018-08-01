package de.axp.framework.testplugin;

import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.extension.PortfolioFrameworkPlugIn;
import de.axp.framework.api.services.TaskServiceInterface;

public class TestPlugIn implements PortfolioFrameworkPlugIn {

	@Override
	public void initialize(AuthenticatedPortfolioFramework framework) {
		framework.getFrameworkTaskService()
				.addTaskHandler("Doge",
						(task, promise) -> promise.respond(TaskServiceInterface.TaskResolution.REJECTED, "Foo"));
	}
}
