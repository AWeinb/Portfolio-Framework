package de.axp.framework.internal.service.task;

import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;

class TaskImpl implements TaskServiceInterface.Task {

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
}
