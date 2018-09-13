package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.internal.services.task.TaskServiceFactory;

public interface TaskService extends FrameworkService {

	void triggerTask(Task task, TaskPromise promise);

	enum TaskResolution {
		RESOLVED, REJECTED, UNHANDLED
	}

	interface Task {

		static Task build(String id, Object content) {
			return TaskServiceFactory.createTask(id, content);
		}

		String getId();

		Object getContent();

	}

	interface TaskResponse {

		static TaskResponse build(String id, Object content, TaskResolution resolution) {
			return TaskServiceFactory.createTaskResponse(id, content, resolution);
		}

		String getId();

		TaskResolution getResolution();

		Object getContent();

	}

	@FunctionalInterface
	interface TaskPromise {

		void respond(TaskResponse response);

	}
}
