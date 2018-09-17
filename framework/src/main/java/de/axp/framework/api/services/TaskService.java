package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.internal.services.task.TaskServiceFactory;

public interface TaskService extends FrameworkService {

	void triggerTask(Task task, TaskPromise promise);

	enum TaskResolution {
		RESOLVED, REJECTED, UNHANDLED
	}

	interface Task {

		static Task build(String handlerId, Object content) {
			return TaskServiceFactory.createTask(handlerId, content);
		}

		String target();

		Object getContent();

	}

	interface TaskResponse {

		static TaskResponse build(Object content, TaskResolution resolution) {
			return TaskServiceFactory.createTaskResponse(content, resolution);
		}

		TaskResolution getResolution();

		Object getContent();

	}

	@FunctionalInterface
	interface TaskPromise {

		void respond(TaskResponse response);

	}
}
