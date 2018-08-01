package de.axp.framework.internal.service.task;

import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;

class TaskResultImpl implements TaskResult {

	private final String taskId;
	private final TaskServiceInterface.TaskResolution resolution;
	private final Object content;

	TaskResultImpl(String taskId, TaskServiceInterface.TaskResolution resolution, Object content) {
		this.taskId = taskId;
		this.resolution = resolution;
		this.content = content;
	}

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public TaskServiceInterface.TaskResolution getResolution() {
		return resolution;
	}

	@Override
	public Object getContent() {
		return content;
	}
}
