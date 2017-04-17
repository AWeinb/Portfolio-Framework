package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.FrameworkInterface.FrameworkResponseListener;

import java.util.LinkedList;
import java.util.List;

class ResponseNotifierImpl implements ResponseNotifier {

	private List<FrameworkResponseListener> responseListeners = new LinkedList<>();

	@Override
	public void addResponseListener(FrameworkResponseListener responseListener) {
		responseListeners.add(responseListener);
	}

	@Override
	public void notifyListeners(String response) {
		for (FrameworkResponseListener responseListener : responseListeners) {
			responseListener.onResponse(response);
		}
	}

	List<FrameworkResponseListener> getResponseListeners() {
		return responseListeners;
	}
}
