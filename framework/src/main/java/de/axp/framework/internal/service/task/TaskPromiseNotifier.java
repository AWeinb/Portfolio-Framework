package de.axp.framework.internal.service.task;

import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.mainloop.MainLoopPackage;
import de.axp.framework.api.MainThreadSynchronization;
import de.axp.framework.api.serviceinterfaces.TaskServiceInterface.TaskPromise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TaskPromiseNotifier implements MainLoop.MainLoopListener {

	private final Map<String, TaskPromise> taskPromises = Collections.synchronizedMap(new HashMap<>());
	private MainThreadSynchronization synchronization;

	void registerPromise(String sessionId, String contextId, String taskId, TaskPromise promise) {
		taskPromises.put(getKey(sessionId, contextId, taskId), promise);
	}

	void setMainThreadSynchronization(MainThreadSynchronization synchronization) {
		this.synchronization = synchronization;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskResult response = (TaskResult) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String contextId = aPackage.getContextId();
		String taskId = response.getTaskId();
		TaskPromise promise = taskPromises.remove(getKey(sessionId, contextId, taskId));

		if (promise != null) {
			if (synchronization == null) {
				promise.respond(response.getResolution(), response.getContent());
			} else {
				synchronization.makeAsyncToSync(() -> promise.respond(response.getResolution(), response.getContent()));
			}
		}
	}

	private String getKey(String sessionId, String contextId, String taskId) {
		return sessionId + "_" + contextId + "_" + taskId;
	}
}
