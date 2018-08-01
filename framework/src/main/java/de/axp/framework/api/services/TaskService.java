package de.axp.framework.api.services;

import de.axp.framework.internal.service.task.TaskServiceFactory;

public interface TaskService {

	void addTaskHandler(TaskHandler taskHandler);

	void addTaskHandler(String contextId, TaskHandler taskHandler);

	void triggerTask(String taskId, Object content, TaskPromise promise);

	void triggerTask(String contextId, String taskId, Object content, TaskPromise promise);

	enum TaskResolution {
		RESOLVED, REJECTED, UNHANDLED
	}

	@FunctionalInterface
	interface TaskHandler {

		void handle(Task task, TaskPromise promise);

	}

	interface Task {

		static Task build(String contextId, String taskId, Object content) {
			return TaskServiceFactory.createTask(contextId, taskId, content);
		}

		String getContextId();

		String getTaskId();

		Object getContent();

	}

	@FunctionalInterface
	interface TaskPromise {

		void respond(TaskResolution resolution, Object content);

	}
}
