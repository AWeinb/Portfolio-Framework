package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.MainThreadSynchronization;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface.Task;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface TaskService extends InternalFrameworkService {

	void setMainThreadSynchronization(MainThreadSynchronization synchronization);

	void register(String sessionId, String contextId, TaskServiceInterface.TaskHandler handler);

	void trigger(String sessionId, String contextId, Task task, TaskServiceInterface.TaskPromise promise);
}
