package de.axp.framework.api;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.authentication.Authentication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrameworkTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testAll() {
		PortfolioFramework.BasePortfolioFramework framework = PortfolioFramework.createBaseFramework();

		Authentication authentication = new Authentication("Doge");
		PortfolioFramework sessionFramework = framework.adaptToAuthentication(authentication);
		TaskService frameworkEventInterface = sessionFramework.getFrameworkTaskService();
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

	private TaskService.TaskHandler getSomeListener() {
		return (task, answerPromise) -> {
			if (task.getContent().equals("A")) {
				answerPromise.respond(TaskService.TaskResolution.REJECTED, task.getContent());
			} else {
				answerPromise.respond(TaskService.TaskResolution.RESOLVED, task.getContent());
			}
		};
	}
}