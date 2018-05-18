package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.InternalFactory;

public interface GlobalFramework {

	static GlobalFramework create() {
		return InternalFactory.createFramework();
	}

	void dispose();

	AuthenticatedFramework authenticate(Authentication authentication);

	interface Authentication {

	}
}
