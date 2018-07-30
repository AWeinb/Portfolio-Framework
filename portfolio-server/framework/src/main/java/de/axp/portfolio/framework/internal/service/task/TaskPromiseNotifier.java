package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.MainThreadSynchronization;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface.TaskPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

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
				promise.on(response.getResolution(), response.getContent());
			} else {
				synchronization.makeAsyncToSync(() -> promise.on(response.getResolution(), response.getContent()));
			}
		}
	}

	private String getKey(String sessionId, String contextId, String taskId) {
		return sessionId + "_" + contextId + "_" + taskId;
	}
}
