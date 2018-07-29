package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.UserSessionAccessor;
import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface TaskService extends InternalFrameworkService {

	void setUserSessionAccessor(UserSessionAccessor accessor);

	void register(String sessionId, String contextId, TaskServiceInterface.TaskHandler handler);

	void trigger(String sessionId, String contextId, Task task, TaskServiceInterface.TaskPromise promise);
}
