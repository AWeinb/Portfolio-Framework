package de.axp.portfolio.framework.api.interfaces;

import de.axp.portfolio.framework.api.FrameworkPromise;

public interface FrameworkCommandInterface {

	void dispatchCommand(String commandID, Object content, FrameworkPromise promise);
}
