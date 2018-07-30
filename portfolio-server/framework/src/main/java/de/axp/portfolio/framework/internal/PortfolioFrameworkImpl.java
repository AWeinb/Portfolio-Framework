package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.PortfolioFramework;
import de.axp.portfolio.framework.api.extension.PortfolioFrameworkPlugIn;
import de.axp.portfolio.framework.internal.authentication.Authentication;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.session.SessionService;

import java.util.List;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;
	private final List<PortfolioFrameworkPlugIn> plugIns;

	PortfolioFrameworkImpl(MainLoop mainLoop, ServiceRegistry serviceRegistry, List<PortfolioFrameworkPlugIn> plugIns) {
		this.mainLoop = mainLoop;
		this.serviceRegistry = serviceRegistry;
		this.plugIns = plugIns;
	}

	@Override
	public void shutdown() {
		serviceRegistry.disposeAll();
		mainLoop.dispose();
	}

	@Override
	public AuthenticatedPortfolioFramework authenticate(String username) {
		InternalFrameworkService internalFrameworkService = serviceRegistry.get(SessionService.class);
		SessionService sessionService = (SessionService) internalFrameworkService;
		Authentication authentication = new Authentication(username);
		FrameworkSession frameworkSession = sessionService.initializeSession(authentication);
		return InternalFactory.createAuthenticatedFramework(serviceRegistry, plugIns, frameworkSession);
	}
}
