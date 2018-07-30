package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.InternalFactory;

public interface PortfolioFramework {

	static PortfolioFramework create() {
		return InternalFactory.createFramework();
	}

	AuthenticatedPortfolioFramework authenticate(String user);

	void shutdown();

}
