package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkSessionInterface;

public interface AuthenticatedFramework {

	void dispose();

	String getSessionID();

	FrameworkSessionInterface getFrameworkSessionInterface();

	FrameworkEventInterface getFrameworkEventInterface();

}
