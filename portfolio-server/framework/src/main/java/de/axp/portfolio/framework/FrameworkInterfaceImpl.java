package de.axp.portfolio.framework;

class FrameworkInterfaceImpl implements FrameworkInterface {

	private final SessionManager sessionManager;

	FrameworkInterfaceImpl(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@Override
	public void initFramework() {
	}

	@Override
	public void disposeFramework() {
	}

	@Override
	public void initSession(String sessionId) {
		sessionManager.initSession(sessionId);
	}

	@Override
	public boolean hasFrameworkActiveSessions() {
		return false;
	}

	@Override
	public void destroySession(String sessionId) {
		sessionManager.destroySession(sessionId);
	}
}
