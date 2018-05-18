package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.FrameworkAuthentication;
import de.axp.portfolio.framework.api.GlobalFramework;
import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopFactory;
import de.axp.portfolio.framework.internal.service.ServiceFactory;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

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
		AuthenticatedFramework.SessionId sessionId = new AuthenticatedFramework.SessionId() {
		};
		return InternalFactory.createAuthenticatedFramework(this, serviceRegistry, sessionId);
	}

	@Override
	public void invalidate(AuthenticatedFramework.SessionId sessionId) {

	}
}
