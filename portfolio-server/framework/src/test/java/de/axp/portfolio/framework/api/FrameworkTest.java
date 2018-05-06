package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.internal.service.event.EventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrameworkTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAll() throws Exception {
		FrameworkExtensions frameworkExtensions = new FrameworkExtensions();
		EventService.EventConsumer eventConsumer = getCommandHandler();
		frameworkExtensions.setEventConsumer(eventConsumer);
		Framework framework = Framework.create(frameworkExtensions);

		SessionFramework sessionFramework = framework.adaptForSession("123");
		sessionFramework.getFrameworkSessionInterface().initializeSession();
		FrameworkEventInterface frameworkEventInterface = sessionFramework.getFrameworkEventInterface();

		frameworkEventInterface.dispatchEvent("Foo", "A", new FrameworkPromise() {

			@Override
			public void reject() {
				Assert.assertEquals("A", this.getFutureData());
			}
		});
		Thread.sleep(100);

		frameworkEventInterface.dispatchEvent("Foo", "B", new FrameworkPromise() {

			@Override
			public void resolve() {
				Assert.assertEquals("B", this.getFutureData());
			}
		});
		sessionFramework.getFrameworkSessionInterface().destroySession();

		framework.dispose();
		Thread.sleep(100);
	}

	private EventService.EventConsumer getCommandHandler() {
		return new EventService.EventConsumer() {

			@Override
			public void setFrameworkReference(Framework framework) {
			}

			@Override
			public void consume(EventService.Event event) {
				if (event.getContent().equals("A")) {
					event.getPromise().get().reject();
				} else {
					event.getPromise().get().resolve();
				}
			}
		};
	}
}