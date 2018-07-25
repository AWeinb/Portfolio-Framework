package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;

public interface AuthenticatedFramework {

	FrameworkSession getSession();

	FrameworkEventInterface getFrameworkEventInterface();

	void invalidate(FrameworkSession session);

}
