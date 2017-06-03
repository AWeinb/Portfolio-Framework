package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkException;
import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

public interface CommandManagement {

	void initialize();

	boolean isInitialized();

	void dispose() throws InterruptedException;

	void dispatchCommand(FrameworkSessionInterface.FrameworkSession session,
	                     FrameworkCommandInterface.Command.CommandMessage message, FrameworkNotice.Promise promise)
			throws InterruptedException;

	class AlreadyInitializedException extends FrameworkException {

		@Override
		public String getMessage() {
			return "This Component was already initialized!";
		}
	}
}
