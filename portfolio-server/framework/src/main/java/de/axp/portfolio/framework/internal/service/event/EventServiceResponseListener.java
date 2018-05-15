package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static de.axp.portfolio.framework.internal.service.event.EventService.Event;

class EventServiceResponseListener implements MainLoop.MainLoopListener {

	private final Map<String, FrameworkPromise> responsePromises = Collections.synchronizedMap(new HashMap<>());

	void registerPromise(String sessionId, String packageId, FrameworkPromise promise) {
		responsePromises.put(getKey(sessionId, packageId), promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		if (aPackage.getState() == MainLoopPackage.STATE.Poisoned) {
			return;
		}

		Event response = (Event) aPackage.getPayload();
		String sessionId = response.getSessionId();
		String packageId = response.getPackageId();
		FrameworkPromise promise = responsePromises.remove(getKey(sessionId, packageId));

		if (promise != null) {
			switch (aPackage.getState()) {
				case Resolved:
					promise.resolve(response.getData());
					break;
				case Rejected:
					promise.reject(response.getData());
					break;
				case Unknown:
					break;
			}
		}
	}

	private String getKey(String sessionId, String packageId) {
		return sessionId + "_" + packageId;
	}
}
