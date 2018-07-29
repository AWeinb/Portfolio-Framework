package de.axp.portfolio.framework.internal.service.event;

public interface Task {

	static Task build(String id, Object content) {
		return new TaskImpl(id, content);
	}

	String getId();

	Object getData();

}
