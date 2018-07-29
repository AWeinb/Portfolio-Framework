package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrameworkTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testAll() {
		GlobalFramework framework = GlobalFramework.create();

		AuthenticatedFramework sessionFramework = framework.authenticate("Hans");
		TaskServiceInterface frameworkEventInterface = sessionFramework.getFrameworkEventInterface();
		frameworkEventInterface.addHandler(getSomeListener());

		frameworkEventInterface.triggerTask("FutureCallback", "A",
				TaskServiceInterface.TaskPromise.builder().onRejection(f -> Assert.assertEquals("A", f)).build());

		frameworkEventInterface.triggerTask("FutureCallback", "B", (resolution, result) -> {
			if (resolution == TaskServiceInterface.TaskPromise.TaskResult.SUCCESS) {
				Assert.assertEquals("B", result);
			}
		});

		framework.shutdown();
	}

	private TaskServiceInterface.TaskHandler getSomeListener() {
		return (event, answerPromise) -> {
			if (event.getData().equals("A")) {
				answerPromise.triggerFailure(event.getData());
			} else {
				answerPromise.triggerSuccess(event.getData());
			}
		};
	}
}