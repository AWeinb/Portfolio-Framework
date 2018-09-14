package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.internal.services.task.TaskServiceFactory;

public interface TaskService extends FrameworkService {

	void triggerTask(Task task, TaskPromise promise);

	enum TaskResolution {
		RESOLVED, REJECTED, UNHANDLED
	}

	interface Task {

		static Task build(String taskId, String handlerId, Object content) {
			return TaskServiceFactory.createTask(taskId, handlerId, content);
		}

		String getTaskId();

		String getHandlerId();

		Object getContent();

	}

	interface TaskResponse {

		static TaskResponse build(String taskId, Object content, TaskResolution resolution) {
			return TaskServiceFactory.createTaskResponse(taskId, content, resolution);
		}

		String getTaskId();

		TaskResolution getResolution();

		Object getContent();

	}

	@FunctionalInterface
	interface TaskPromise {

		void respond(TaskResponse response);

	}
}
