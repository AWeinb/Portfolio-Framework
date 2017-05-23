package de.axp.portfolio.framework;

public interface FrameworkCommandInterface extends FrameworkInterface {

	void dispatchCommand(CommandMessage commandMessage) throws InterruptedException;

	interface CommandMessage extends FrameworkMessage {

	}
}
