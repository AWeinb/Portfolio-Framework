package de.axp.portfolio.framework;

public interface ResponseNotifier {

	void addResponseListener(FrameworkInterface.FrameworkResponseListener responseListener);

	void notifyListeners(String response);
}
