package de.axp.framework.api.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.FrameworkService;

public interface UiService extends FrameworkService {

	void registerPortfolioDefinition(PortfolioDefinition definition);

	Set<PortfolioDefinition> getPortfolioDefinitions();

	Optional<PortfolioDefinition> getPortfolioDefinition(String portfolioId);

	interface PortfolioDefinition extends FrameworkPlugin {

		String getPortfolioId();

		List<Class<? extends PortfolioPart>> getPortfolioParts();

	}

	interface PortfolioPart {

		String getPartId();

	}
}
