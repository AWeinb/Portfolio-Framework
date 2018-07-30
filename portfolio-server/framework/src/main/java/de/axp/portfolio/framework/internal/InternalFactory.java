package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.PortfolioFramework;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

public class InternalFactory {

	public static PortfolioFramework createFramework() {
		return new PortfolioFrameworkImpl();
	}

	static AuthenticatedPortfolioFramework createAuthenticatedFramework(ServiceRegistry serviceRegistry,
	                                                                    FrameworkSession session) {
		return new AuthenticatedPortfolioFrameworkImpl(serviceRegistry, session);
	}
}
