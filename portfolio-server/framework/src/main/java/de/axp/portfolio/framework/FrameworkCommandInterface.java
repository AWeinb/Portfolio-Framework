package de.axp.portfolio.framework;

public interface FrameworkCommandInterface {

	void deinitialize();

	void putCommand(String command) throws InterruptedException;

	void addResponseListener(FrameworkResponseListener responseListener);

	interface FrameworkResponseListener {

		void onResponse(String response);
	}
}
