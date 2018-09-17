package de.axp.framework.api;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.TaskService;

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

		AtomicBoolean isCalled = new AtomicBoolean(false);

		TaskService.Task task1 = TaskService.Task.build("Buzz", "A");
		frameworkEventInterface.triggerTask(task1, response -> {
			if (response.getResolution() == TaskService.TaskResolution.REJECTED) {
				Assert.assertEquals("A", response.getContent());
			} else {
				Assert.fail();
			}
			isCalled.set(true);
		});

		TaskService.Task task2 = TaskService.Task.build("Buzz", "B");
		frameworkEventInterface.triggerTask(task2, response -> {
			if (response.getResolution() == TaskService.TaskResolution.RESOLVED) {
				Assert.assertEquals("B", response.getContent());
			} else {
				Assert.fail();
			}
			isCalled.set(true);
		});

		framework.shutdown();

		if (!isCalled.get()) {
			Assert.fail();
		}
	}

	private TaskHandler getSomeListener() {
		return new TaskHandler() {

			@Override
			public String handlerId() {
				return "Buzz";
			}

			@Override
			public void handle(TaskService.Task task, TaskService.TaskPromise promise) {
				if (task.getContent().equals("A")) {
					TaskService.TaskResponse taskResponse = TaskService.TaskResponse.build(task.getContent(),
							TaskService.TaskResolution.REJECTED);
					promise.respond(taskResponse);
				} else {
					TaskService.TaskResponse taskResponse = TaskService.TaskResponse.build(task.getContent(),
							TaskService.TaskResolution.RESOLVED);
					promise.respond(taskResponse);
				}
			}
		};
	}
}
