package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

public interface MessageHandlerInterface {

	void handleMessage(FrameworkSessionInterface.FrameworkSession session, FrameworkNotice.Message commandMessage,
	                   FrameworkNotice.Promise promiseToResolveOrReject);
}
