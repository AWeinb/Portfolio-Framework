package de.axp.portfolio.framework;

public interface FrameworkSessionInterface extends FrameworkInterface {

	void initializeSession(String sessionID);

	boolean hasFrameworkActiveSessions();

	void destroySession(String sessionID);

	interface FrameworkSession {

	}
}
