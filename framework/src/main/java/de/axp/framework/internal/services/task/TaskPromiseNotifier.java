package de.axp.framework.internal.services.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TaskService.TaskPromise;
import de.axp.framework.internal.services.task.mainloop.MainLoopListener;
import de.axp.framework.internal.services.task.mainloop.MainLoopPackage;

class TaskPromiseNotifier implements MainLoopListener {

	private final Map<String, TaskPromise> taskPromises = Collections.synchronizedMap(new HashMap<>());

	void registerPromise(TaskService.Task task, TaskPromise promise) {
		taskPromises.put(((TaskImpl) task).getTaskId(), promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskResponseImpl response = (TaskResponseImpl) aPackage.getPayload();
		String taskId = response.getTaskId();
		TaskPromise promise = taskPromises.remove(taskId);
		promise.respond(response);
	}
}
