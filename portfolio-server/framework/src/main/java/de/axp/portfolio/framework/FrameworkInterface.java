package de.axp.portfolio.framework;

public interface FrameworkInterface {

	void initializeSession();

	void destroySession();

	void putCommand(String command) throws InterruptedException;

	interface FrameworkResponseListener {

		void onResponse(String response);
	}
}
