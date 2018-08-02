package de.axp.framework.internal.services.task;

import de.axp.framework.api.FrameworkThreadSynchronizer;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TaskService.Task;
import de.axp.framework.internal.services.BaseFrameworkService;

public interface BaseTaskService extends BaseFrameworkService {

	void setMainThreadSynchronization(FrameworkThreadSynchronizer synchronization);

	void register(String sessionId, String contextId, TaskService.TaskHandler handler);

	void trigger(String sessionId, Task task, TaskService.TaskPromise promise);
}
