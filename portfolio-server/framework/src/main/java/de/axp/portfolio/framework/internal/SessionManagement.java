package de.axp.portfolio.framework.internal;

public interface SessionManagement {

	void initializeSession(String sessionId);

	void disposeSession(String sessionId);
}
