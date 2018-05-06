package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;

import java.util.Optional;

class EventImpl implements EventService.Event {

	private String sessionID;
	private String packageID;
	private Object content;
	private FrameworkPromise promise;

	EventImpl(String sessionID, String packageID, Object content, FrameworkPromise promise) {
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

	@Override
	public Optional<FrameworkPromise> getPromise() {
		return Optional.ofNullable(promise);
	}
}
