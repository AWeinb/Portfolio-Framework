package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskResponseImpl implements TaskService.TaskResponse {

	private final String contextId;
	private final String taskId;
	private final TaskService.TaskResolution resolution;
	private final Object content;

	TaskResponseImpl(String contextId, String taskId, TaskService.TaskResolution resolution, Object content) {
		this.contextId = contextId;
		this.taskId = taskId;
		this.resolution = resolution;
		this.content = content;
	}

	@Override
	public String getContextId() {
		return contextId;
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
		return super.toString() + "(" + getContextId() + ", " + getTaskId() + ", " + resolution + ", " + getContent() + ")";
	}
}
