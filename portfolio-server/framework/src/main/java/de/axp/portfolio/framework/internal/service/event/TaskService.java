package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface TaskService extends InternalFrameworkService {

	void register(String sessionId, String context, TaskServiceInterface.TaskHandler handler);

	void trigger(String sessionId, String context, Task task, TaskServiceInterface.TaskPromise promise);
}
