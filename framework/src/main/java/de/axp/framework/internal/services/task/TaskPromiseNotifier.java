package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TaskService.TaskPromise;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TaskPromiseNotifier implements MainLoop.MainLoopListener {

	private final Map<String, TaskPromise> taskPromises = Collections.synchronizedMap(new HashMap<>());

	void registerPromise(String taskId, TaskPromise promise) {
		taskPromises.put(taskId, promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskService.TaskResponse response = (TaskService.TaskResponse) aPackage.getPayload();
		String taskId = response.getTaskId();
		TaskPromise promise = taskPromises.remove(taskId);
		promise.respond(response);
	}
}
