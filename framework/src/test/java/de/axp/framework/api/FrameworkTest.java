package de.axp.framework.api;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrameworkTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testAll() {
		PortfolioFramework framework = PortfolioFramework.createFramework();
		PluginService pluginService = framework.getPluginService();
		pluginService.addPlugin(TaskHandler.class, getSomeListener());

		TaskService frameworkEventInterface = framework.getTaskService();

		TaskService.Task task1 = TaskService.Task.build("", "Foo", "A");
		frameworkEventInterface.triggerTask(task1, (resolution, result) -> {
			if (resolution == TaskService.TaskResolution.REJECTED) {
				Assert.assertEquals("A", result);
			} else {
				Assert.fail();
			}
		});

		TaskService.Task task2 = TaskService.Task.build("", "Bar", "B");
		frameworkEventInterface.triggerTask(task2, (resolution, result) -> {
			if (resolution == TaskService.TaskResolution.RESOLVED) {
				Assert.assertEquals("B", result);
			} else {
				Assert.fail();
			}
		});

		framework.shutdown();
	}

	private TaskHandler getSomeListener() {
		return new TaskHandler() {

			@Override
			public void handle(TaskService.Task task, TaskService.TaskPromise promise) {
				if (task.getContent().equals("A")) {
					promise.respond(TaskService.TaskResolution.REJECTED, task.getContent());
				} else {
					promise.respond(TaskService.TaskResolution.RESOLVED, task.getContent());
				}
			}

			@Override
			public String provideIdentifier() {
				return "";
			}
		};
	}
}
