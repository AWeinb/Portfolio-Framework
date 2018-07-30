package de.axp.portfolio.framework.api.extension;

import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;

public interface PortfolioFrameworkPlugIn {

	void initialize(AuthenticatedPortfolioFramework framework);
}
