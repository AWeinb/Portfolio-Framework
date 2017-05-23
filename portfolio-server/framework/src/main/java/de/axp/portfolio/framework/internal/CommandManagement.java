package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkMessage;
import de.axp.portfolio.framework.FrameworkSessionInterface;

public interface CommandManagement {

	void initialize();

	void dispose() throws InterruptedException;

	void dispatchCommand(FrameworkSessionInterface.FrameworkSession session, FrameworkMessage.Message message,
	                     FrameworkMessage.Promise promise) throws InterruptedException;
}
