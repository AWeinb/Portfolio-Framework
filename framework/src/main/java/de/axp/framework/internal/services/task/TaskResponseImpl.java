package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskResponseImpl implements TaskService.TaskResponse {

	private final String id;
	private final Object content;
	private final TaskService.TaskResolution resolution;

	TaskResponseImpl(String id, Object content, TaskService.TaskResolution resolution) {
		this.id = id;
		this.content = content;
		this.resolution = resolution;
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
	public TaskService.TaskResolution getResolution() {
		return resolution;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + getId() + ", " + getContent() + ", " + resolution + ")";
	}
}
