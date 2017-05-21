package de.axp.portfolio.framework;

public interface ResponseNotifier {

	void addResponseListener(FrameworkResponseListener responseListener);

	void notifyListeners(String response);

	interface FrameworkResponseListener {
		void onResponse(String response);
	}
}
