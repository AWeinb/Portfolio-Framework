package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.BaseFramework;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

public class InternalFrameworkFactory {

	public static BaseFramework createBaseFramework() {
		return new BaseFrameworkImpl();
	}

	static AuthenticatedFramework createAuthenticatedFramework(BaseFramework framework, ServiceRegistry serviceRegistry,
	                                                           AuthenticatedFramework.SessionId sessionId) {
		return new AuthenticatedFrameworkImpl(framework, serviceRegistry, sessionId);
	}
}
