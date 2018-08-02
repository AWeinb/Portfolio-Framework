package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.BasePortfolioFramework;
import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.services.InternalFrameworkService;
import de.axp.framework.internal.services.ServiceRegistry;
import de.axp.framework.internal.services.session.InternalSessionService;

import java.util.List;

class BasePortfolioFrameworkImpl implements BasePortfolioFramework {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;
	private final List<FrameworkPlugin> plugins;

	BasePortfolioFrameworkImpl(MainLoop mainLoop, ServiceRegistry serviceRegistry, List<FrameworkPlugin> plugins) {
		this.mainLoop = mainLoop;
		this.serviceRegistry = serviceRegistry;
		this.plugins = plugins;
	}

	@Override
	public void shutdown() {
		mainLoop.dispose();
	}

	@Override
	public PortfolioFramework authenticate(String username) {
		InternalFrameworkService service = serviceRegistry.get(InternalSessionService.class);
		InternalSessionService internalSessionService = (InternalSessionService) service;
		Authentication authentication = new Authentication(username);
		SessionService.FrameworkSession frameworkSession = internalSessionService.initializeSession(authentication);
		return InternalFactory.createAuthenticatedFramework(serviceRegistry, plugins, frameworkSession);
	}
}
