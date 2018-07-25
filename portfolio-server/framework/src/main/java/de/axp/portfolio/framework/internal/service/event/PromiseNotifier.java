package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface.EventPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class PromiseNotifier implements MainLoop.MainLoopListener {

	private final Map<String, EventPromise> responsePromises = Collections.synchronizedMap(new HashMap<>());

	void registerPromise(String sessionId, String packageId, EventPromise promise) {
		responsePromises.put(getKey(sessionId, packageId), promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		if (aPackage.getState() == MainLoopPackage.STATE.Poisoned) {
			return;
		}

		Notification response = (Notification) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String id = response.getId();
		EventPromise promise = responsePromises.remove(getKey(sessionId, id));

		if (promise != null) {
			mapPackageStateToPromise(aPackage, response.getData(), promise);
		}
	}

	private String getKey(String sessionId, String packageId) {
		return sessionId + "_" + packageId;
	}

	private void mapPackageStateToPromise(MainLoopPackage aPackage, Object responseData, EventPromise promise) {
		switch (aPackage.getState()) {
			case Resolved:
				promise.on(EventPromise.EventPromiseResult.SUCCESS, responseData);
				break;
			case Rejected:
				promise.on(EventPromise.EventPromiseResult.REJECTION, responseData);
				break;
			case Unknown:
				break;
		}
	}
}
