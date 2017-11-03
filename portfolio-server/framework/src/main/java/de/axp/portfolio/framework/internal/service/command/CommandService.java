package de.axp.portfolio.framework.internal.service.command;

import de.axp.portfolio.framework.api.interaction.FrameworkPackage;
import de.axp.portfolio.framework.api.interaction.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.FrameworkService;

public interface CommandService extends FrameworkService {

	void dispatchCommand(String sessionID, String packageID, FrameworkPackage commandPackage)
			throws InterruptedException;

	interface CommandHandler {

		void execute(String sessionID, String commandID, Object content, FrameworkPromise promiseToResolveOrReject);
	}
}
