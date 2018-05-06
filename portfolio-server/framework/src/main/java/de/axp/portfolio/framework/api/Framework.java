package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.InternalFrameworkFactory;

public interface Framework {

	static Framework create() {
		return InternalFrameworkFactory.createFramework();
	}

	void dispose();

	SessionFramework adaptForSession(String sessionID);

	void addAttribute(String key, Object value);

	Object getAttribute(String key);
}
