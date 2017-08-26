package de.axp.portfolio.framework.api;

public interface FrameworkCommandInterface {

	void dispatchCommand(String commandID, Object content, FrameworkPromise promise);
}
