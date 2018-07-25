package de.axp.portfolio.framework.internal.service.event;

public interface Notification {

	static Notification build(String id, Object content) {
		return new NotificationImpl(id, content);
	}

	String getId();

	Object getData();
}
