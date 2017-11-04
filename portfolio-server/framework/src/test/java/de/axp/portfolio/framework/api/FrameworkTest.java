package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interaction.FrameworkExtensions;
import de.axp.portfolio.framework.api.interaction.FrameworkPromise;
import de.axp.portfolio.framework.api.interfaces.FrameworkCommandInterface;
import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.ui.UiService;
import de.axp.portfolio.framework.internal.service.ui.model.UFrame;
import org.junit.Before;
import org.junit.Test;

public class FrameworkTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAll() throws Exception {
		FrameworkExtensions frameworkExtensions = new FrameworkExtensions();
		CommandService.CommandHandler commandHandler = getCommandHandler();
		UiService.UiChangeHandler uiChangeHandler = getUiChangeHandler();
		frameworkExtensions.setCommandHandler(commandHandler);
		frameworkExtensions.setUiChangeHandler(uiChangeHandler);
		Framework framework = Framework.create(frameworkExtensions);

		SessionFramework sessionFramework = framework.adaptForSession("123");
		sessionFramework.getFrameworkSessionInterface().initializeSession();
		FrameworkCommandInterface frameworkCommandInterface = sessionFramework.getFrameworkCommandInterface();

		frameworkCommandInterface.dispatchCommand("Foo", "A", new FrameworkPromise() {

			@Override
			public void reject() {
				System.out.println(getFutureData());
			}
		});
		Thread.sleep(100);

		frameworkCommandInterface.dispatchCommand("Foo", "B", new FrameworkPromise() {

			@Override
			public void resolve() {
				System.out.println(getFutureData());
			}
		});
		sessionFramework.getFrameworkSessionInterface().destroySession();

		framework.dispose();
		Thread.sleep(100);
	}

	private CommandService.CommandHandler getCommandHandler() {
		return (session, commandMessage, content, promiseToResolveOrReject) -> {
			if (content.equals("A")) {
				promiseToResolveOrReject.reject();
			} else {
				promiseToResolveOrReject.resolve();
			}
		};
	}

	private UiService.UiChangeHandler getUiChangeHandler() {
		return (sessionID, uFrame) -> {
		};
	}
}