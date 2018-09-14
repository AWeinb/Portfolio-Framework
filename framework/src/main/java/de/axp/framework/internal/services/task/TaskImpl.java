package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskImpl implements TaskService.Task {

	private final String taskId;
	private final String handlerId;
	private final Object content;

	TaskImpl(String taskId, String handlerId, Object content) {
		this.taskId = taskId;
		this.handlerId = handlerId;
		this.content = content;
	}

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public String getHandlerId() {
		return handlerId;
	}

	@Override
	public Object getContent() {
		return content;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + getTaskId() + ", " + getHandlerId() + ", " + getContent() + ")";
	}
}
