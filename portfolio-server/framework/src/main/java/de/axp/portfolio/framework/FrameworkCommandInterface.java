package de.axp.portfolio.framework;

public interface FrameworkCommandInterface extends FrameworkInterface {

	void dispatchCommand(Command command) throws InterruptedException;

	interface Command extends FrameworkNotice {

		@Override
		CommandMessage getMessage();

		interface CommandMessage extends Message {

		}
	}
}
