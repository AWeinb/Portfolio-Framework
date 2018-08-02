package de.axp.framework.api;

import de.axp.framework.internal.InternalFactory;

public interface BasePortfolioFramework {

	static BasePortfolioFramework create() {
		return InternalFactory.createFramework();
	}

	PortfolioFramework authenticate(String user);

	void shutdown();

}
