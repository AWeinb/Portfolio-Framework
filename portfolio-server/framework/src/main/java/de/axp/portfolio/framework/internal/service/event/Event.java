package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.FrameworkPackage;

public class Event implements FrameworkPackage {

	private String sessionID;
	private String packageID;
	private Object content;
	private FrameworkPromise promise;

	public Event(String sessionID, String packageID, Object content, FrameworkPromise promise) {
		this.sessionID = sessionID;
		this.packageID = packageID;
		this.content = content;
		this.promise = promise;
	}

	@Override
	public String getSessionID() {
		return sessionID;
	}

	@Override
	public String getPackageID() {
		return packageID;
	}

	@Override
	public Object getContent() {
		return content;
	}

	public FrameworkPromise getPromise() {
		return promise;
	}
}
