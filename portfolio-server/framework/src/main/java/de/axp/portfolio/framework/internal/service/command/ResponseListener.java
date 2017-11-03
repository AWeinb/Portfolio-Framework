package de.axp.portfolio.framework.internal.service.command;

import de.axp.portfolio.framework.api.interaction.FrameworkPackage;
import de.axp.portfolio.framework.api.interaction.FrameworkPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.HashMap;
import java.util.Map;

class ResponseListener implements MainLoop.MainLoopListener {

	private final Map<String, FrameworkPromise> responsePromises = new HashMap<>();

	void registerPromise(String sessionID, String packageID, FrameworkPromise promise) {
		responsePromises.put(getKey(sessionID, packageID), promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		FrameworkPackage frameworkPackage = aPackage.getFrameworkPackage();

		String sessionID = frameworkPackage.getSessionID();
		String packageID = frameworkPackage.getPackageID();

		FrameworkPromise promise = responsePromises.remove(getKey(sessionID, packageID));
		promise.setFutureOutput(frameworkPackage.getContent());

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
