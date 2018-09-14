package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskImpl implements TaskService.Task {

	private final String taskId;
	private final Object content;

	TaskImpl(String taskId, Object content) {
		this.taskId = taskId;
		this.content = content;
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
	public String toString() {
		return super.toString() + "(" + getTaskId() + ", " + getContent() + ")";
	}
}
