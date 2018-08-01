package de.axp.framework.api;

import de.axp.framework.internal.InternalFactory;

public interface PortfolioFramework {

	static PortfolioFramework create() {
		return InternalFactory.createFramework();
	}

	AuthenticatedPortfolioFramework authenticate(String user);

	void shutdown();

}
