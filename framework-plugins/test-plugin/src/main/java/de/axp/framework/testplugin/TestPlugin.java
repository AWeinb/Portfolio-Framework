package de.axp.framework.testplugin;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.internal.infrastructure.plugin.FrameworkPlugin;
import de.axp.framework.api.services.TaskService;

public class TestPlugin implements FrameworkPlugin {

	@Override
	public void initialize(PortfolioFramework framework) {
		framework.getTaskService()
				.addTaskHandler("Doge",
						(task, promise) -> promise.respond(TaskService.TaskResolution.REJECTED, "Foo"));
	}
}
