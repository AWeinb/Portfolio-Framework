package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
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
		FrameworkEventInterface frameworkEventInterface = sessionFramework.getFrameworkEventInterface();
		frameworkEventInterface.addListener(getSomeListener());

		frameworkEventInterface.fireEvent("FutureCallback", "A",
				FrameworkEventInterface.EventPromise.builder().onRejection(f -> Assert.assertEquals("A", f)).build());

		frameworkEventInterface.fireEvent("FutureCallback", "B", (resolution, result) -> {
			if (resolution == FrameworkEventInterface.EventPromise.EventPromiseResult.SUCCESS) {
				Assert.assertEquals("B", result);
			}
		});

		framework.shutdown();
	}

	private FrameworkEventInterface.EventListener getSomeListener() {
		return (event, answerPromise) -> {
			if (event.getData().equals("A")) {
				answerPromise.triggerFailure(event.getData());
			} else {
				answerPromise.triggerSuccess(event.getData());
			}
		};
	}
}