package de.axp.framework.api.extensions;

import de.axp.framework.api.services.TaskService;

@FunctionalInterface
public interface TaskHandler {

	void handle(TaskService.Task task, TaskService.TaskPromise promise);

}
