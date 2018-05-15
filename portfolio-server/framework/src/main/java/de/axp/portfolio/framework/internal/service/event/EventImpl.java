package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;

import java.util.Optional;

class EventImpl implements EventService.Event {

	private final String sessionId;
	private final String context;
	private final String packageId;
	private final Object content;
	private final FrameworkPromise promise;

	EventImpl(String sessionId, String context, String packageId, Object content, FrameworkPromise promise) {
		this.sessionId = sessionId;
		this.context = context;
		this.packageId = packageId;
		this.content = content;
		this.promise = promise;
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public String getContext() {
		return context;
	}

	@Override
	public String getPackageId() {
		return packageId;
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
