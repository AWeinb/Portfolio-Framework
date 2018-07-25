package de.axp.portfolio.framework.internal.service.event;

public interface Event {

	static Event build(String id, Object content) {
		return new EventImpl(id, content);
	}

	String getId();

	Object getData();

}
