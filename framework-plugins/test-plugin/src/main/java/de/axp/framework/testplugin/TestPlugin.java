package de.axp.framework.testplugin;

import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.services.TaskService;

public class TestPlugin implements FrameworkPlugin {

	@Override
	public void initialize(AuthenticatedPortfolioFramework framework) {
		framework.getFrameworkTaskService()
				.addTaskHandler("Doge",
						(task, promise) -> promise.respond(TaskService.TaskResolution.REJECTED, "Foo"));
	}
}
