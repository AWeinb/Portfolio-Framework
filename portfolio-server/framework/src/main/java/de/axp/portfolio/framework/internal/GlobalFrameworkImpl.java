package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.FrameworkAuthentication;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.GlobalFramework;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopFactory;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;
import de.axp.portfolio.framework.internal.service.ServiceFactory;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.session.SessionService;

class GlobalFrameworkImpl implements GlobalFramework {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;

	GlobalFrameworkImpl() {
		mainLoop = MainLoopFactory.createMainLoop();
		serviceRegistry = ServiceFactory.createServiceRegistry(mainLoop);
	}

	@Override
	public void dispose() {
		serviceRegistry.disposeAll();
		mainLoop.dispose();
	}

	@Override
	public AuthenticatedFramework authenticate(FrameworkAuthentication authentication) {
		InternalFrameworkService internalFrameworkService = serviceRegistry.get(SessionService.class);
		SessionService sessionService = (SessionService) internalFrameworkService;
		FrameworkSession frameworkSession = sessionService.initializeSession(authentication);
		return InternalFactory.createAuthenticatedFramework(serviceRegistry, frameworkSession);
	}
}
