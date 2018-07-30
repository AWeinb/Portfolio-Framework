package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrameworkTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testAll() {
		PortfolioFramework framework = PortfolioFramework.create();

		AuthenticatedPortfolioFramework sessionFramework = framework.authenticate("Hans");
		TaskServiceInterface frameworkEventInterface = sessionFramework.getFrameworkTaskService();
		frameworkEventInterface.addTaskHandler(getSomeListener());

		frameworkEventInterface.triggerTask("FutureCallback", "A", (resolution, result) -> {
			if (resolution == TaskServiceInterface.TaskResolution.REJECTED) {
				Assert.assertEquals("A", result);
			}
		});

		frameworkEventInterface.triggerTask("FutureCallback", "B", (resolution, result) -> {
			if (resolution == TaskServiceInterface.TaskResolution.RESOLVED) {
				Assert.assertEquals("B", result);
			}
		});

		framework.shutdown();
	}

	private TaskServiceInterface.TaskHandler getSomeListener() {
		return (task, answerPromise) -> {
			if (task.getContent().equals("A")) {
				answerPromise.respond(TaskServiceInterface.TaskResolution.REJECTED, task.getContent());
			} else {
				answerPromise.respond(TaskServiceInterface.TaskResolution.RESOLVED, task.getContent());
			}
		};
	}
}