package de.axp.portfolio.framework;

public interface FrameworkCommandInterface extends FrameworkInterface {

	void dispatchCommand(FrameworkNotice command) throws InterruptedException;
}
