package de.axp.portfolio.framework.api.interfaces;

import de.axp.portfolio.framework.api.FrameworkPromise;

public interface FrameworkEventInterface {

	void dispatchEvent(String commandID, Object content, FrameworkPromise promise);
}
