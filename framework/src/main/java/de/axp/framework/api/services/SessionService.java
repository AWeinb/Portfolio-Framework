package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkSession;

public interface SessionService {

	FrameworkSession getSession();

	void invalidate();
}
