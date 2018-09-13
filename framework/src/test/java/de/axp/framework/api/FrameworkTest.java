package de.axp.framework.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.axp.framework.api.extensions.TaskHandler;
import de.axp.framework.api.services.TaskService;

public class FrameworkTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testAll() {
		PortfolioFramework framework = PortfolioFramework.createFramework();
		TaskService frameworkEventInterface = framework.getTaskService();
		frameworkEventInterface.addTaskHandler(getSomeListener());

		frameworkEventInterface.triggerTask("FutureCallback", "A", (resolution, result) -> {
			if (resolution == TaskService.TaskResolution.REJECTED) {
				Assert.assertEquals("A", result);
			}
		});

		frameworkEventInterface.triggerTask("FutureCallback", "B", (resolution, result) -> {
			if (resolution == TaskService.TaskResolution.RESOLVED) {
				Assert.assertEquals("B", result);
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
