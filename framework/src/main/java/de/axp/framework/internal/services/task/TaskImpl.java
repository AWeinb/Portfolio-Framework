package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

class TaskImpl implements TaskService.Task {

	private final String handlerId;
	private final Object content;
	private final String taskId;

	TaskImpl(String handlerId, Object content) {
		this.handlerId = handlerId;
		this.content = content;
		this.taskId = String.valueOf(this.hashCode());
	}

	@Override
	public String target() {
		return handlerId;
	}

	@Override
	public Object getContent() {
		return content;
	}

	String getTaskId() {
		return taskId;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + target() + ", " + getContent() + ")";
	}

}
