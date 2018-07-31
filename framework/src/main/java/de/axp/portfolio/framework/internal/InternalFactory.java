package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.PortfolioFramework;
import de.axp.portfolio.framework.api.extension.PortfolioFrameworkPlugIn;
import de.axp.portfolio.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.portfolio.framework.internal.extension.ExtensionFactory;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopFactory;
import de.axp.portfolio.framework.internal.service.ServiceFactory;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.session.SessionFactory;
import de.axp.portfolio.framework.internal.service.task.TaskServiceFactory;

import java.util.List;

public class InternalFactory {

	public static PortfolioFramework createFramework() {
		MainLoop mainLoop = MainLoopFactory.createMainLoop();
		ServiceRegistry serviceRegistry = ServiceFactory.createServiceRegistry(mainLoop);
		List<PortfolioFrameworkPlugIn> plugIns = ExtensionFactory.getPlugIns();
		return new PortfolioFrameworkImpl(mainLoop, serviceRegistry, plugIns);
	}

	static AuthenticatedPortfolioFramework createAuthenticatedFramework(ServiceRegistry serviceRegistry,
	                                                                    List<PortfolioFrameworkPlugIn> plugIns,
	                                                                    FrameworkSession session) {
		SessionServiceInterface sessionServiceInterface = SessionFactory.createSessionServiceInterface(serviceRegistry,
				session);
		TaskServiceInterface taskServiceInterface = TaskServiceFactory.createTaskServiceInterface(serviceRegistry,
				session);
		AuthenticatedPortfolioFrameworkImpl authenticatedPortfolioFramework = new AuthenticatedPortfolioFrameworkImpl(
				serviceRegistry, session, sessionServiceInterface, taskServiceInterface);
		for (PortfolioFrameworkPlugIn plugIn : plugIns) {
			plugIn.initialize(authenticatedPortfolioFramework);
		}
		return authenticatedPortfolioFramework;
	}
}
