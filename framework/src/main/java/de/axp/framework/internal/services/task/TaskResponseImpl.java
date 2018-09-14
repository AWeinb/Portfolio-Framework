package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskResponseImpl implements TaskService.TaskResponse {

	private final String taskId;
	private final Object content;
	private final TaskService.TaskResolution resolution;

	TaskResponseImpl(String taskId, Object content, TaskService.TaskResolution resolution) {
		this.taskId = taskId;
		this.content = content;
		this.resolution = resolution;
	}

	@Override
	public String getTaskId() {
		return taskId;
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
		return super.toString() + "(" + getTaskId() + ", " + getContent() + ", " + resolution + ")";
	}
}
