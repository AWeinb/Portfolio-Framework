package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.InternalFrameworkFactory;

public interface SessionFramework {

	static UninitializedFramework create() {
		return InternalFrameworkFactory.createUninitializedFramework();
	}

	String getSessionID();

	FrameworkSessionInterface getFrameworkSessionInterface();

	FrameworkCommandInterface getFrameworkCommandInterface();

	FrameworkUiInterface getFrameworkUiInterface();
}
