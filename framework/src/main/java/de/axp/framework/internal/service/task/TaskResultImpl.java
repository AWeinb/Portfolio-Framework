package de.axp.framework.internal.service.task;

import de.axp.framework.api.services.TaskService;

class TaskResultImpl implements TaskResult {

	private final String taskId;
	private final TaskService.TaskResolution resolution;
	private final Object content;

	TaskResultImpl(String taskId, TaskService.TaskResolution resolution, Object content) {
		this.taskId = taskId;
		this.resolution = resolution;
		this.content = content;
	}

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public TaskService.TaskResolution getResolution() {
		return resolution;
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + getTaskId() + ", " + resolution + ", " + getContent() + ")";
	}
}
