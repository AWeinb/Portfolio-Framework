package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;

public interface TaskResult {

	static TaskResult build(String taskId, TaskServiceInterface.TaskResolution resolution, Object content) {
		return new TaskResultImpl(taskId, resolution, content);
	}

	String getTaskId();

	TaskServiceInterface.TaskResolution getResolution();

	Object getContent();
}
