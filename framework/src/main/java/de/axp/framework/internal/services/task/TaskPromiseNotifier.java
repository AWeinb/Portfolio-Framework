package de.axp.framework.internal.services.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.axp.framework.api.services.TaskService.TaskPromise;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;

class TaskPromiseNotifier implements MainLoop.MainLoopListener {

	private final Map<String, TaskPromise> taskPromises = Collections.synchronizedMap(new HashMap<>());

	void registerPromise(String taskId, TaskPromise promise) {
		taskPromises.put(taskId, promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskResult response = (TaskResult) aPackage.getPayload();
		String taskId = response.getTaskId();
		TaskPromise promise = taskPromises.remove(taskId);

		if (promise != null) {
			promise.respond(response.getResolution(), response.getContent());
		}
	}
}
