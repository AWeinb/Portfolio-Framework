package de.axp.portfolio.framework.api.interfaces;

import de.axp.portfolio.framework.api.FrameworkExternalHandler;
import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.event.Task;
import de.axp.portfolio.framework.internal.service.event.TaskPromiseBuilder;

public interface TaskServiceInterface {

	void addHandler(TaskHandler taskHandler);

	void addHandler(String context, TaskHandler taskHandler);

	void triggerTask(String eventID, Object content, TaskPromise promise);

	void triggerTask(String context, String eventID, Object content, TaskPromise promise);

	@FunctionalInterface
	interface TaskHandler extends FrameworkExternalHandler {

		void handle(Task event, ResultCallback answer);

		interface ResultCallback extends FrameworkPromise {

			void triggerSuccess(Object result);

			void triggerFailure(Object result);
		}
	}

	interface TaskPromise extends FrameworkPromise {

		static TaskPromiseBuilder builder() {
			return new TaskPromiseBuilder();
		}

		void on(TaskResult resolution, Object result);

		enum TaskResult {
			SUCCESS, REJECTION
		}
	}
}
