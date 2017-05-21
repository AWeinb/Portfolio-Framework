package de.axp.portfolio.framework;

public interface FrameworkInterface {

	void initFramework();

	void disposeFramework();

	void initSession(String sessionId);

	boolean hasFrameworkActiveSessions();

	void destroySession(String sessionId);
}
