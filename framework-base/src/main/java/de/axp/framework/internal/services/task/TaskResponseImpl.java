package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskResponseImpl implements TaskService.TaskResponse {

	private final Object content;
	private final TaskService.TaskResolution resolution;
	private String taskId;

	TaskResponseImpl(Object content, TaskService.TaskResolution resolution) {
		this.content = content;
		this.resolution = resolution;
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public TaskService.TaskResolution getResolution() {
		return resolution;
	}

	void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	String getTaskId() {
		return taskId;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + getContent() + ", " + resolution + ")";
	}
}
