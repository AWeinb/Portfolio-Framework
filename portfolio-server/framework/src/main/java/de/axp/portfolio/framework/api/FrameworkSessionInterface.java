package de.axp.portfolio.framework.api;

public interface FrameworkSessionInterface {

	void initializeSession();

	boolean hasFrameworkActiveSessions();

	void destroySession();
}
