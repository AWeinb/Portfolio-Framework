package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.FrameworkCommandInterface;
import de.axp.portfolio.framework.internal.service.command.CommandService;
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
		frameworkExtensions.setCommandHandler(commandHandler);
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
		return new CommandService.CommandHandler() {

			@Override
			public void setFrameworkReference(Framework framework) {
			}

			@Override
			public void execute(String sessionID, String commandID, Object content,
			                    FrameworkPromise promiseToResolveOrReject) {
				if (content.equals("A")) {
					promiseToResolveOrReject.reject();
				} else {
					promiseToResolveOrReject.resolve();
				}
			}
		};
	}
}