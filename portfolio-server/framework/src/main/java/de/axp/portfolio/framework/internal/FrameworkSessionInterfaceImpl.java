package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkSessionInterface;

class FrameworkSessionInterfaceImpl implements FrameworkSessionInterface {

	private final SessionManagement sessionManagement;

	FrameworkSessionInterfaceImpl(SessionManagement sessionManagement) {
		this.sessionManagement = sessionManagement;
	}

	@Override
	public void initialize() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void initializeSession(String sessionID) {
		sessionManagement.initializeSession(sessionID);
	}

	@Override
	public boolean hasFrameworkActiveSessions() {
		return false;
	}

	@Override
	public void destroySession(String sessionID) {
		sessionManagement.disposeSession(sessionID);
	}
}
