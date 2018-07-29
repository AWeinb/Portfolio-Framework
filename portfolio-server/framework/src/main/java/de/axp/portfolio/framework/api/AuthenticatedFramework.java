package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;

public interface AuthenticatedFramework {

	FrameworkSession getSession();

	void setUserSessionAccessor(UserSessionAccessor accessor);

	TaskServiceInterface getFrameworkTaskService();

	void invalidate(FrameworkSession session);

}
