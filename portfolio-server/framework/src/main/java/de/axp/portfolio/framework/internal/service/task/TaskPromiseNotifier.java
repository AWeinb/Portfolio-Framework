package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.UserSessionAccessor;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface.TaskPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TaskPromiseNotifier implements MainLoop.MainLoopListener {

	private final Map<String, TaskPromise> taskPromises = Collections.synchronizedMap(new HashMap<>());
	private UserSessionAccessor sessionAccessor;

	void registerPromise(String sessionId, String contextId, String taskId, TaskPromise promise) {
		taskPromises.put(getKey(sessionId, contextId, taskId), promise);
	}

	void setUserSessionAccessor(UserSessionAccessor sessionAccessor) {
		this.sessionAccessor = sessionAccessor;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		Notification response = (Notification) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String contextId = aPackage.getContextId();
		String taskId = response.getTaskId();
		TaskPromise promise = taskPromises.remove(getKey(sessionId, contextId, taskId));

		if (promise != null) {
			if (sessionAccessor == null) {
				promise.on(response.getResolution(), response.getContent());
			} else {
				sessionAccessor.makeAsyncToSync(() -> promise.on(response.getResolution(), response.getContent()));
			}
		}
	}

	private String getKey(String sessionId, String contextId, String taskId) {
		return sessionId + "_" + contextId + "_" + taskId;
	}
}
