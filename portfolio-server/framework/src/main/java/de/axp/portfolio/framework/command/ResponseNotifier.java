package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.FrameworkInterface;

public interface ResponseNotifier {

	void addResponseListener(FrameworkInterface.FrameworkResponseListener responseListener);

	void notifyListeners(String response);
}
