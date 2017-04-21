package de.axp.portfolio.framework;

public interface FrameworkInterface {

	void initFramework();

	boolean isFrameworkInitialized();

	boolean hasFrameworkActiveSessions();

	void deinitFramework();

	void initSession(String sessionId);

	SessionState testSessionId(String sessionId);

	void destroySession(String sessionId);

	void putCommand(String command) throws InterruptedException;

	void addListener(FrameworkResponseListener responseListener);

	interface FrameworkResponseListener {
		void onResponse(String response);
	}
}
