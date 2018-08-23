package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.extensions.TaskHandler;
import de.axp.framework.internal.services.task.TaskServiceFactory;

public interface TaskService extends FrameworkService {

	void addTaskHandler(TaskHandler taskHandler);

	void triggerTask(String taskId, Object content, TaskPromise promise);

	void triggerTask(String contextId, String taskId, Object content, TaskPromise promise);

	enum TaskResolution {
		RESOLVED, REJECTED, UNHANDLED
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
