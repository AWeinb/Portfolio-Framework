package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.PortfolioFramework;
import de.axp.portfolio.framework.internal.authentication.Authentication;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopFactory;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;
import de.axp.portfolio.framework.internal.service.ServiceFactory;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.session.SessionService;

class PortfolioFrameworkImpl implements PortfolioFramework {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;

	PortfolioFrameworkImpl() {
		mainLoop = MainLoopFactory.createMainLoop();
		serviceRegistry = ServiceFactory.createServiceRegistry(mainLoop);
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
		return InternalFactory.createAuthenticatedFramework(serviceRegistry, frameworkSession);
	}
}
