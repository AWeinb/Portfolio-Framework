package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.PortfolioFramework;
import de.axp.portfolio.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.session.SessionFactory;
import de.axp.portfolio.framework.internal.service.task.TaskServiceFactory;

public class InternalFactory {

	public static PortfolioFramework createFramework() {
		return new PortfolioFrameworkImpl();
	}

	static AuthenticatedPortfolioFramework createAuthenticatedFramework(ServiceRegistry serviceRegistry,
	                                                                    FrameworkSession session) {
		SessionServiceInterface sessionServiceInterface = SessionFactory.createSessionServiceInterface(serviceRegistry,
				session);
		TaskServiceInterface taskServiceInterface = TaskServiceFactory.createTaskServiceInterface(serviceRegistry,
				session);
		return new AuthenticatedPortfolioFrameworkImpl(serviceRegistry, session, sessionServiceInterface,
				taskServiceInterface);
	}
}
