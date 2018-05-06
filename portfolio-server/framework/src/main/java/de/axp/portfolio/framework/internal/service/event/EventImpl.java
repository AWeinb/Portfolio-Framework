package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;

import java.util.Optional;

class EventImpl implements EventService.Event {

	private final String sessionID;
	private final String context;
	private final String packageID;
	private final Object content;
	private final FrameworkPromise promise;

	EventImpl(String sessionID, String context, String packageID, Object content, FrameworkPromise promise) {
		this.sessionID = sessionID;
		this.context = context;
		this.packageID = packageID;
		this.content = content;
		this.promise = promise;
	}

	@Override
	public String getSessionID() {
		return sessionID;
	}

	@Override
	public String getContext() {
		return context;
	}

	@Override
	public String getPackageID() {
		return packageID;
	}

	@Override
	public Object getData() {
		return content;
	}

	@Override
	public Optional<FrameworkPromise> getPromise() {
		return Optional.ofNullable(promise);
	}
}
