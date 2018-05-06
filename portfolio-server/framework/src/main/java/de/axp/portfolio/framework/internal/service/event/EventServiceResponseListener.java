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

	void registerPromise(String sessionID, String packageID, FrameworkPromise promise) {
		responsePromises.put(getKey(sessionID, packageID), promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		Event response = (Event) aPackage.getFrameworkPackage();
		String sessionID = response.getSessionID();
		String packageID = response.getPackageID();

		FrameworkPromise promise = responsePromises.remove(getKey(sessionID, packageID));
		promise.setFutureOutput(response.getContent());

		if (aPackage.getState() == MainLoopPackage.STATE.Resolved) {
			promise.resolve();
		} else {
			promise.reject();
		}
	}

	private String getKey(String sessionID, String packageID) {
		return sessionID + "_" + packageID;
	}
}
