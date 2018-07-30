package de.axp.portfolio.framework.api.serviceinterfaces;

import de.axp.portfolio.framework.api.FrameworkSession;

public interface SessionServiceInterface {

	FrameworkSession getSession();

	void invalidate();
}
