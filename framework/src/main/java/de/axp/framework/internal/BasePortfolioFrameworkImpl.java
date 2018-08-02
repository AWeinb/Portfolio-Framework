package de.axp.framework.internal;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.plugin.PluginRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry;
import de.axp.framework.internal.services.session.BaseSessionService;
import de.axp.framework.internal.services.session.SessionServiceFactory;
import de.axp.framework.internal.services.task.TaskServiceFactory;

class BasePortfolioFrameworkImpl implements PortfolioFramework.BasePortfolioFramework {

	private final MainLoop mainLoop;
	private final BaseServiceRegistry baseServiceRegistry;
	private final PluginRegistry pluginRegistry;

	BasePortfolioFrameworkImpl(MainLoop mainLoop, BaseServiceRegistry baseServiceRegistry,
	                           PluginRegistry pluginRegistry) {
		this.mainLoop = mainLoop;
		this.baseServiceRegistry = baseServiceRegistry;
		this.pluginRegistry = pluginRegistry;
	}

	@Override
	public PortfolioFramework adaptToAuthentication(Authentication authentication) {
		BaseSessionService internalSessionService = baseServiceRegistry.getBaseService(BaseSessionService.class);
		SessionService.FrameworkSession session = internalSessionService.initializeSession(authentication);

		SessionService sessionService = SessionServiceFactory.createSessionService(baseServiceRegistry, session);
		TaskService taskService = TaskServiceFactory.createTaskService(baseServiceRegistry, session);

		PortfolioFrameworkImpl authenticatedPortfolioFramework = new PortfolioFrameworkImpl(baseServiceRegistry,
				session, sessionService, taskService);

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
