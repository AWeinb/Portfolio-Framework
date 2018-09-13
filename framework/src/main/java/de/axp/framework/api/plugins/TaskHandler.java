package de.axp.framework.api.plugins;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.plugin.FrameworkPlugin;

public interface TaskHandler extends FrameworkPlugin {

	boolean isRelevant(String id);

	void handle(TaskService.Task task, TaskService.TaskPromise promise);

}
