package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkSession;

public interface SessionServiceInterface {

	FrameworkSession getSession();

	void invalidate();
}
