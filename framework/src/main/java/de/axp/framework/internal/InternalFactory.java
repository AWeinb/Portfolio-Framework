package de.axp.framework.internal;

import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.extension.PortfolioFrameworkPlugIn;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.extension.ExtensionFactory;
import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.mainloop.MainLoopFactory;
import de.axp.framework.internal.service.ServiceFactory;
import de.axp.framework.internal.service.ServiceRegistry;
import de.axp.framework.internal.service.interfaces.InterfaceFactory;

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
	                                                                    SessionService.FrameworkSession session) {
		SessionService sessionService = InterfaceFactory.createSessionServiceInterface(serviceRegistry, session);
		TaskService taskService = InterfaceFactory.createTaskServiceInterface(serviceRegistry, session);

		AuthenticatedPortfolioFrameworkImpl authenticatedPortfolioFramework = new AuthenticatedPortfolioFrameworkImpl(
				serviceRegistry, session, sessionService, taskService);

		for (PortfolioFrameworkPlugIn plugIn : plugIns) {
			plugIn.initialize(authenticatedPortfolioFramework);
		}

		return authenticatedPortfolioFramework;
	}
}
