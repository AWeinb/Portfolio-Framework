package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.FrameworkPlugin;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry;
import de.axp.framework.internal.services.session.BaseSessionService;

import java.util.Collection;

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
	public PortfolioFramework adaptToUser(Authentication authentication) {
		BaseSessionService internalSessionService = baseServiceRegistry.getBaseService(BaseSessionService.class);
		SessionService.FrameworkSession session = internalSessionService.initializeSession(authentication);

		BaseServiceRegistry.AuthenticatedServiceRegistry authenticatedServiceRegistry = baseServiceRegistry.adaptToSession(
				session, baseServiceRegistry);

		PortfolioFrameworkImpl authenticatedPortfolioFramework = new PortfolioFrameworkImpl(
				authenticatedServiceRegistry);

		Collection<? extends FrameworkPlugin> plugins = pluginRegistry.getPlugins();
		for (FrameworkPlugin plugin : plugins) {
			plugin.initialize(authenticatedPortfolioFramework);
		}

		return authenticatedPortfolioFramework;
	}

	@Override
	public void shutdown() {
		mainLoop.dispose();
	}
}
