package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.InternalFactory;

public interface GlobalFramework {

	static GlobalFramework create() {
		return InternalFactory.createFramework();
	}

	AuthenticatedFramework authenticate(FrameworkAuthentication authentication);

	void shutdown();

}
