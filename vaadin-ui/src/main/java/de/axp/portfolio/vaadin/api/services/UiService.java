package de.axp.portfolio.vaadin.api.services;

import java.util.Set;

import de.axp.framework.api.FrameworkService;
import de.axp.portfolio.vaadin.api.plugins.PortfolioDefinition;

public interface UiService extends FrameworkService {

	void registerPortfolioDefinition(PortfolioDefinition definition);

	Set<PortfolioDefinition> getPortfolioDefinitions();

	PortfolioDefinition getPortfolioDefinition(String portfolioId);
}
