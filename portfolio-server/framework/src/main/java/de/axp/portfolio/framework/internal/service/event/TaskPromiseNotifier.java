package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface.TaskPromise;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

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
		if (aPackage.getState() == MainLoopPackage.STATE.Poisoned) {
			return;
		}

		Notification response = (Notification) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String id = response.getId();
		TaskPromise promise = taskPromises.remove(getKey(sessionId, id));

		if (promise != null) {
			mapPackageStateToPromise(aPackage, response.getData(), promise);
		}
	}

	private String getKey(String sessionId, String packageId) {
		return sessionId + "_" + packageId;
	}

	private void mapPackageStateToPromise(MainLoopPackage aPackage, Object responseData, TaskPromise promise) {
		switch (aPackage.getState()) {
			case Resolved:
				promise.on(TaskPromise.TaskResult.SUCCESS, responseData);
				break;
			case Rejected:
				promise.on(TaskPromise.TaskResult.REJECTION, responseData);
				break;
			case Unknown:
				break;
		}
	}
}
