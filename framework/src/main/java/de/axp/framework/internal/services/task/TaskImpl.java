package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskImpl implements TaskService.Task {

	private final String id;
	private final Object content;

	TaskImpl(String id, Object content) {
		this.id = id;
		this.content = content;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + getId() + ", " + getContent() + ")";
	}
}
