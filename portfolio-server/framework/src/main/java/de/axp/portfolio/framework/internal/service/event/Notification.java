package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;

public interface Notification {

	static Notification build(String taskId, TaskServiceInterface.TaskResolution resolution, Object content) {
		return new NotificationImpl(taskId, resolution, content);
	}

	String getTaskId();

	TaskServiceInterface.TaskResolution getResolution();

	Object getContent();
}
