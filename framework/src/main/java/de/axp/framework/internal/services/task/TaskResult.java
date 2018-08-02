package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;

interface TaskResult {

	static TaskResult build(String taskId, TaskService.TaskResolution resolution, Object content) {
		return new TaskResultImpl(taskId, resolution, content);
	}

	String getTaskId();

	TaskService.TaskResolution getResolution();

	Object getContent();
}
