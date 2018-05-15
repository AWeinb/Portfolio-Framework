package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.InternalFrameworkFactory;

public interface BaseFramework {

	static BaseFramework create() {
		return InternalFrameworkFactory.createBaseFramework();
	}

	void dispose();

	AuthenticatedFramework adaptForSession(String sessionID);

	void addAttribute(String key, Object value);

	Object getAttribute(String key);
}
