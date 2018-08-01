package de.axp.framework.internal;

import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.extension.PortfolioFrameworkPlugIn;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.service.InternalFrameworkService;
import de.axp.framework.internal.service.ServiceRegistry;
import de.axp.framework.internal.service.session.InternalSessionService;

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
		InternalFrameworkService service = serviceRegistry.get(InternalSessionService.class);
		InternalSessionService internalSessionService = (InternalSessionService) service;
		Authentication authentication = new Authentication(username);
		SessionService.FrameworkSession frameworkSession = internalSessionService.initializeSession(authentication);
		return InternalFactory.createAuthenticatedFramework(serviceRegistry, plugIns, frameworkSession);
	}
}
