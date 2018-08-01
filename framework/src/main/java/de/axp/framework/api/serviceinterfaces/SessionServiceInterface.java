package de.axp.framework.api.serviceinterfaces;

import de.axp.framework.api.FrameworkSession;

public interface SessionServiceInterface {

	FrameworkSession getSession();

	void invalidate();
}
