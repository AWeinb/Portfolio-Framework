package de.axp.portfolio.vaadin.internal.pages.portfolio;

import de.axp.portfolio.vaadin.FallbackPortfolioDefinition;

import static de.axp.framework.api.services.UiService.PortfolioDefinition;

public final class PortfolioPageState {

	private PortfolioDefinition portfolioDefinition = new FallbackPortfolioDefinition();
	private int portfolioPartIndex;

	public PortfolioDefinition getPortfolioDefinition() {
		return portfolioDefinition;
	}

	void setPortfolioDefinition(PortfolioDefinition portfolioDefinition) {
		this.portfolioDefinition = portfolioDefinition;
	}

	public int getPortfolioPartIndex() {
		return portfolioPartIndex;
	}

	void setPortfolioPartIndex(int i) {
		this.portfolioPartIndex = i;
	}
}
