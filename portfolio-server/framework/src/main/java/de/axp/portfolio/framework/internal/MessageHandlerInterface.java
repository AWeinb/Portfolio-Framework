package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkMessage;
import de.axp.portfolio.framework.FrameworkSessionInterface;

public interface MessageHandlerInterface {

	void handleMessage(FrameworkSessionInterface.FrameworkSession session, FrameworkMessage.Message commandMessage,
	                   ResponsePromise responsePromise);

	interface ResponsePromise extends FrameworkMessage.Promise {

	}
}
