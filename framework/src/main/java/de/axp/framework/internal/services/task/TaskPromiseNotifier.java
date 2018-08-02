package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService.TaskPromise;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TaskPromiseNotifier implements MainLoop.MainLoopListener {

	private final Map<String, TaskPromise> taskPromises = Collections.synchronizedMap(new HashMap<>());

	void registerPromise(String sessionId, String taskId, TaskPromise promise) {
		taskPromises.put(getKey(sessionId, taskId), promise);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskResult response = (TaskResult) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String taskId = response.getTaskId();
		TaskPromise promise = taskPromises.remove(getKey(sessionId, taskId));

		if (promise != null) {
			promise.respond(response.getResolution(), response.getContent());
		}
	}

	private String getKey(String sessionId, String taskId) {
		return sessionId + "_" + taskId;
	}
}
