package de.axp.framework.internal.service.task;

import de.axp.framework.api.MainThreadSynchronization;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TaskService.Task;
import de.axp.framework.internal.service.InternalFrameworkService;

public interface InternalTaskService extends InternalFrameworkService {

	void setMainThreadSynchronization(MainThreadSynchronization synchronization);

	void register(String sessionId, String contextId, TaskService.TaskHandler handler);

	void trigger(String sessionId, Task task, TaskService.TaskPromise promise);
}
