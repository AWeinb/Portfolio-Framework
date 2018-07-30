package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface.TaskHandler;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TaskHandlerNotifier implements MainLoop.MainLoopListener {

	private final MainLoop.MainLoopAccessor outputBufferAccessor;
	private final Map<String, Map<String, TaskHandler>> handlers = Collections.synchronizedMap(new HashMap<>());

	TaskHandlerNotifier(MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.outputBufferAccessor = outputBufferAccessor;
	}

	void addHandler(String sessionId, String context, TaskHandler handler) {
		if (!handlers.containsKey(sessionId)) {
			handlers.put(sessionId, Collections.synchronizedMap(new HashMap<>()));
		}
		handlers.get(sessionId).put(context, handler);
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskServiceInterface.Task task = (TaskServiceInterface.Task) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String contextId = aPackage.getContextId();
		String taskId = task.getTaskId();

		TaskServiceInterface.TaskPromise callback = (resolution, result) -> {
			TaskResult response = TaskResult.build(taskId, resolution, result);
			MainLoopPackage packedResponse = new MainLoopPackage(sessionId, contextId, response);
			outputBufferAccessor.put(packedResponse);
		};

		Map<String, TaskHandler> handlerMap = handlers.get(aPackage.getSessionId());
		TaskHandler handler = handlerMap.get(aPackage.getContextId());
		handler.handle(task, callback);
	}

}
