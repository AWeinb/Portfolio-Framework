package de.axp.framework.api.serviceinterfaces;

import de.axp.framework.internal.service.task.TaskServiceFactory;

public interface TaskServiceInterface {

	void addTaskHandler(TaskHandler taskHandler);

	void addTaskHandler(String contextId, TaskHandler taskHandler);

	void triggerTask(String taskId, Object content, TaskPromise promise);

	void triggerTask(String contextId, String taskId, Object content, TaskPromise promise);

	enum TaskResolution {
		RESOLVED, REJECTED
	}

	@FunctionalInterface
	interface TaskHandler {

		void handle(Task task, TaskPromise promise);

	}

	interface Task {

		static Task build(String taskId, Object content) {
			return TaskServiceFactory.createTask(taskId, content);
		}

		String getTaskId();

		Object getContent();

	}

	@FunctionalInterface
	interface TaskPromise {

		void respond(TaskResolution resolution, Object content);

	}
}
