package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.FrameworkService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UiService extends FrameworkService {

	void registerPortfolioDefinition(PortfolioDefinition definition);

	Set<PortfolioDefinition> getPortfolioDefinitions();

	Optional<PortfolioDefinition> getPortfolioDefinition(String portfolioId);

	interface PortfolioDefinition extends FrameworkPlugin {

		String getPortfolioId();

		List<? extends PortfolioPart> getPortfolioParts();

	}

	interface PortfolioPart<T> {

		String getPartId();

		T getUiComponent();
	}
}
