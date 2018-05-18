package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;

public interface AuthenticatedFramework {

	void dispose();

	FrameworkSession getSession();

	FrameworkEventInterface getFrameworkEventInterface();

}
