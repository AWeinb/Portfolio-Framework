package de.axp.framework.api.extension;

import de.axp.framework.api.AuthenticatedPortfolioFramework;

public interface FrameworkPlugin {

	void initialize(AuthenticatedPortfolioFramework framework);
}
