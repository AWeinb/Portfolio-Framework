package de.axp.framework.api.plugins;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.services.TaskService;

public interface TaskHandler extends FrameworkPlugin {

	void handle(TaskService.Task task, TaskService.TaskPromise promise);

	String handlerId();
}
