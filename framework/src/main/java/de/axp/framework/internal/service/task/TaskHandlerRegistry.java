package de.axp.framework.internal.service.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static de.axp.framework.api.services.TaskServiceInterface.TaskHandler;

class TaskHandlerRegistry {

	private final Map<String, Map<String, TaskHandler>> handlers = Collections.synchronizedMap(new HashMap<>());

	void setHandler(String sessionId, String context, TaskHandler handler) {
		if (!handlers.containsKey(sessionId)) {
			handlers.put(sessionId, Collections.synchronizedMap(new HashMap<>()));
		}
		handlers.get(sessionId).put(context, handler);
	}

	TaskHandler getTaskHandler(String sessionId, String contextId) {
		Map<String, TaskHandler> handlerMap = handlers.get(sessionId);
		return handlerMap.get(contextId);
	}
}
