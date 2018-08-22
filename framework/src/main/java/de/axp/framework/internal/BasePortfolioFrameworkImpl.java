package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.authentication.Authentication;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.services.BaseServiceRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry.AuthenticatedServiceRegistry;
import de.axp.framework.internal.services.session.BaseSessionService;

class BasePortfolioFrameworkImpl implements PortfolioFramework.BasePortfolioFramework {

	private final MainLoop mainLoop;
	private final BaseServiceRegistry baseServiceRegistry;

	BasePortfolioFrameworkImpl(MainLoop mainLoop, BaseServiceRegistry baseServiceRegistry) {
		this.mainLoop = mainLoop;
		this.baseServiceRegistry = baseServiceRegistry;
	}

	@Override
	public PortfolioFramework adaptToUser(Authentication authentication) {
		BaseSessionService internalSessionService = baseServiceRegistry.getBaseService(BaseSessionService.class);
		SessionService.FrameworkSession session = internalSessionService.initializeSession(authentication);

		AuthenticatedServiceRegistry serviceRegistry = baseServiceRegistry.adaptToSession(session, baseServiceRegistry);
		return new PortfolioFrameworkImpl(serviceRegistry);
	}

	@Override
	public void shutdown() {
		mainLoop.dispose();
	}
}
