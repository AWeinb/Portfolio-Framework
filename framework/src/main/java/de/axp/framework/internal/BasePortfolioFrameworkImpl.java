package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.services.BaseServiceRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry.AuthenticatedServiceRegistry;

class BasePortfolioFrameworkImpl implements PortfolioFramework.BasePortfolioFramework {

	private final MainLoop mainLoop;
	private final BaseServiceRegistry baseServiceRegistry;

	BasePortfolioFrameworkImpl(MainLoop mainLoop, BaseServiceRegistry baseServiceRegistry) {
		this.mainLoop = mainLoop;
		this.baseServiceRegistry = baseServiceRegistry;
	}

	@Override
	public PortfolioFramework adaptToUser(Authentication authentication) {
		PortfolioFramework.FrameworkSession session = new PortfolioFramework.FrameworkSession() {
		};
		AuthenticatedServiceRegistry serviceRegistry = baseServiceRegistry.adaptToSession(session, baseServiceRegistry);
		return new PortfolioFrameworkImpl(serviceRegistry);
	}

	@Override
	public void shutdown() {
		mainLoop.dispose();
	}
}
