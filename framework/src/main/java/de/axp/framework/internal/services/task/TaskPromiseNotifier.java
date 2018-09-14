package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TaskService.TaskPromise;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopListener;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TaskPromiseNotifier implements MainLoopListener {

	private final Map<String, TaskPromise> taskPromises = Collections.synchronizedMap(new HashMap<>());

	void registerPromise(String taskId, TaskPromise promise) {
		taskPromises.put(taskId, promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskService.TaskResponse response = (TaskService.TaskResponse) aPackage.getPayload();
		String id = response.getId();
		TaskPromise promise = taskPromises.remove(id);
		promise.respond(response);
	}
}
