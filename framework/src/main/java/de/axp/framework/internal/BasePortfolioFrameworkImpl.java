package de.axp.framework.internal;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.plugin.PluginRegistry;
import de.axp.framework.internal.services.InternalFrameworkService;
import de.axp.framework.internal.services.ServiceRegistry;
import de.axp.framework.internal.services.session.InternalSessionService;
import de.axp.framework.internal.services.session.SessionServiceFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;

class BasePortfolioFrameworkImpl implements PortfolioFramework.BasePortfolioFramework {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;
	private final PluginRegistry pluginRegistry;

	BasePortfolioFrameworkImpl(MainLoop mainLoop, ServiceRegistry serviceRegistry, PluginRegistry pluginRegistry) {
		this.mainLoop = mainLoop;
		this.serviceRegistry = serviceRegistry;
		this.pluginRegistry = pluginRegistry;
	}

	@Override
	public PortfolioFramework adaptToUser(String username) {
		InternalFrameworkService service = serviceRegistry.get(InternalSessionService.class);
		InternalSessionService internalSessionService = (InternalSessionService) service;
		Authentication authentication = new Authentication(username);
		SessionService.FrameworkSession session = internalSessionService.initializeSession(authentication);

		SessionService sessionService = SessionServiceFactory.createSessionService(serviceRegistry, session);
		TaskService taskService = TaskServiceFactory.createTaskService(serviceRegistry, session);

		PortfolioFrameworkImpl authenticatedPortfolioFramework = new PortfolioFrameworkImpl(serviceRegistry, session,
				sessionService, taskService);

		for (FrameworkPlugin plugin : pluginRegistry.getPlugins()) {
			plugin.initialize(authenticatedPortfolioFramework);
		}
		return authenticatedPortfolioFramework;
	}

	@Override
	public void shutdown() {
		mainLoop.dispose();
	}
}
