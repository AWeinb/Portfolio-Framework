package de.axp.framework.internal.service.task;

import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;

interface TaskResult {

	static TaskResult build(String taskId, TaskServiceInterface.TaskResolution resolution, Object content) {
		return new TaskResultImpl(taskId, resolution, content);
	}

	String getTaskId();

	TaskServiceInterface.TaskResolution getResolution();

	Object getContent();
}
