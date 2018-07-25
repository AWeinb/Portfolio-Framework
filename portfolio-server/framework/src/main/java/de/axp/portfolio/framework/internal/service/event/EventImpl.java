package de.axp.portfolio.framework.internal.service.event;

class EventImpl implements Event {

	private final String id;
	private final Object content;

	EventImpl(String id, Object content) {
		this.id = id;
		this.content = content;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Object getData() {
		return content;
	}
}
