package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.BaseFramework;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopFactory;
import de.axp.portfolio.framework.internal.service.ServiceFactory;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

class BaseFrameworkImpl implements BaseFramework {

	private MainLoop mainLoop;
	private ServiceRegistry serviceRegistry;

	BaseFrameworkImpl() {
		mainLoop = MainLoopFactory.createMainLoop();
		serviceRegistry = ServiceFactory.createServiceRegistry(mainLoop);
	}

	@Override
	public void dispose() {
		serviceRegistry.disposeAll();
		mainLoop.dispose();
	}

	@Override
	public AuthenticatedFramework adaptForSession(String sessionID) {
		return InternalFrameworkFactory.createAuthenticatedFramework(this, sessionID);
	}

	ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
}
