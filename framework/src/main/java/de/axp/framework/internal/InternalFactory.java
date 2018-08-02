package de.axp.framework.internal;

import de.axp.framework.api.BasePortfolioFramework;
import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopFactory;
import de.axp.framework.internal.plugin.ExtensionFactory;
import de.axp.framework.internal.services.ServiceRegistry;
import de.axp.framework.internal.services.session.SessionServiceFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;

import java.util.List;

public class InternalFactory {

	public static BasePortfolioFramework createFramework() {
		MainLoop mainLoop = MainLoopFactory.createMainLoop();
		ServiceRegistry serviceRegistry = new ServiceRegistry(mainLoop);
		List<FrameworkPlugin> plugins = ExtensionFactory.getPlugins();
		return new BasePortfolioFrameworkImpl(mainLoop, serviceRegistry, plugins);
	}

	static PortfolioFramework createAuthenticatedFramework(ServiceRegistry serviceRegistry,
	                                                       List<FrameworkPlugin> plugins,
	                                                       SessionService.FrameworkSession session) {
		SessionService sessionService = SessionServiceFactory.createSessionService(serviceRegistry, session);
		TaskService taskService = TaskServiceFactory.createTaskService(serviceRegistry, session);

		PortfolioFrameworkImpl authenticatedPortfolioFramework = new PortfolioFrameworkImpl(serviceRegistry, session,
				sessionService, taskService);

		for (FrameworkPlugin plugin : plugins) {
			plugin.initialize(authenticatedPortfolioFramework);
		}

		return authenticatedPortfolioFramework;
	}
}
