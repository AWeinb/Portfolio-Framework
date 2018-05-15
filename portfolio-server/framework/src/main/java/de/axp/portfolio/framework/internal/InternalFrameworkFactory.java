package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.BaseFramework;

public class InternalFrameworkFactory {

	public static BaseFramework createBaseFramework() {
		return new BaseFrameworkImpl();
	}

	static AuthenticatedFramework createAuthenticatedFramework(BaseFrameworkImpl framework, String sessionID) {
		return new AuthenticatedFrameworkImpl(framework, sessionID);
	}
}
