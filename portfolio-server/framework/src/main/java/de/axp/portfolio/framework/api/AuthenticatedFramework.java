package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkSessionInterface;

public interface AuthenticatedFramework {

	void dispose();

	FrameworkSession getSession();

	FrameworkSessionInterface getFrameworkSessionInterface();

	FrameworkEventInterface getFrameworkEventInterface();

}
