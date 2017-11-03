package de.axp.portfolio.framework.api.interfaces;

public interface FrameworkSessionInterface {

	void initializeSession();

	boolean hasFrameworkActiveSessions();

	void destroySession();
}
