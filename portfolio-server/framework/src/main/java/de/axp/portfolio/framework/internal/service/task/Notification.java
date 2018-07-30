package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;

public interface Notification {

	static Notification build(String taskId, TaskServiceInterface.TaskResolution resolution, Object content) {
		return new NotificationImpl(taskId, resolution, content);
	}

	String getTaskId();

	TaskServiceInterface.TaskResolution getResolution();

	Object getContent();
}
