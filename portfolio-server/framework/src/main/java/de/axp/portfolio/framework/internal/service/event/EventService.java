package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.FrameworkHandler;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface EventService extends InternalFrameworkService {

	void dispatchEvent(String sessionID, String packageID, Event event) throws InterruptedException;

	interface EventHandler extends FrameworkHandler {

		void execute(String sessionID, String commandID, Object content, FrameworkPromise promiseToResolveOrReject);
	}
}
