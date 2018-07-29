package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;

class NotificationImpl implements Notification {

	private final String taskId;
	private final TaskServiceInterface.TaskResolution resolution;
	private final Object content;

	NotificationImpl(String taskId, TaskServiceInterface.TaskResolution resolution, Object content) {
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
