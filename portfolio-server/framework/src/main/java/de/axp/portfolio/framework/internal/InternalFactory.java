package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.GlobalFramework;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

public class InternalFactory {

	public static GlobalFramework createFramework() {
		return new GlobalFrameworkImpl();
	}

	static AuthenticatedFramework createAuthenticatedFramework(GlobalFramework framework,
	                                                           ServiceRegistry serviceRegistry,
	                                                           AuthenticatedFramework.SessionId sessionId) {
		return new AuthenticatedFrameworkImpl(framework, serviceRegistry, sessionId);
	}
}
