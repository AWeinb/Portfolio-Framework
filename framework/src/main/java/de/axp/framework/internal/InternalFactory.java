package de.axp.framework.internal;

import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.extension.FrameworkPlugIn;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.extension.ExtensionFactory;
import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.mainloop.MainLoopFactory;
import de.axp.framework.internal.service.InternalFrameworkService;
import de.axp.framework.internal.service.ServiceRegistry;
import de.axp.framework.internal.service.session.InternalSessionService;
import de.axp.framework.internal.service.session.SessionServiceFactory;
import de.axp.framework.internal.service.task.InternalTaskService;
import de.axp.framework.internal.service.task.TaskServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InternalFactory {

	public static PortfolioFramework createFramework() {
		MainLoop mainLoop = MainLoopFactory.createMainLoop();

		Map<Class, InternalFrameworkService> internalFrameworkServices = new HashMap<>();
		internalFrameworkServices.put(InternalTaskService.class,
				TaskServiceFactory.createInternalTaskService(mainLoop));
		internalFrameworkServices.put(InternalSessionService.class,
				SessionServiceFactory.createInternalSessionService());

		ServiceRegistry serviceRegistry = new ServiceRegistry(internalFrameworkServices);
		List<FrameworkPlugIn> plugIns = ExtensionFactory.getPlugIns();
		return new PortfolioFrameworkImpl(mainLoop, serviceRegistry, plugIns);
	}

	static AuthenticatedPortfolioFramework createAuthenticatedFramework(ServiceRegistry serviceRegistry,
	                                                                    List<FrameworkPlugIn> plugIns,
	                                                                    SessionService.FrameworkSession session) {
		SessionService sessionService = SessionServiceFactory.createSessionService(serviceRegistry, session);
		TaskService taskService = TaskServiceFactory.createTaskService(serviceRegistry, session);

		AuthenticatedPortfolioFrameworkImpl authenticatedPortfolioFramework = new AuthenticatedPortfolioFrameworkImpl(
				serviceRegistry, session, sessionService, taskService);

		for (FrameworkPlugIn plugIn : plugIns) {
			plugIn.initialize(authenticatedPortfolioFramework);
		}

		return authenticatedPortfolioFramework;
	}
}
