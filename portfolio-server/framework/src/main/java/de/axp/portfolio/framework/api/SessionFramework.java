package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.api.interaction.FrameworkExtensions;
import de.axp.portfolio.framework.api.interfaces.FrameworkCommandInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkSessionInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkUiInterface;
import de.axp.portfolio.framework.internal.InternalFrameworkFactory;

public interface SessionFramework {

	static Framework create(FrameworkExtensions frameworkExtensions) {
		return InternalFrameworkFactory.createFramework(frameworkExtensions);
	}

	void dispose();

	void addAttribute(String key, Object value);

	Object getAttribute(String key);

	String getSessionID();

	FrameworkSessionInterface getFrameworkSessionInterface();

	FrameworkCommandInterface getFrameworkCommandInterface();

	FrameworkUiInterface getFrameworkUiInterface();
}
