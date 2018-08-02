package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;

public interface SessionService extends FrameworkService {

	FrameworkSession getSession();

	void validate();

	void invalidate();

	interface FrameworkSession {

	}
}
