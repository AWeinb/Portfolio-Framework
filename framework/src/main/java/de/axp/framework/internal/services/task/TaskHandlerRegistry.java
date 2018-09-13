package de.axp.framework.internal.services.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.axp.framework.api.extensions.TaskHandler;

class TaskHandlerRegistry {

	private final Map<String, TaskHandler> handlers = Collections.synchronizedMap(new HashMap<>());

	void setHandler(String context, TaskHandler handler) {
		handlers.put(context, handler);
	}

	TaskHandler getTaskHandler(String contextId) {
		return handlers.get(contextId);
	}
}
