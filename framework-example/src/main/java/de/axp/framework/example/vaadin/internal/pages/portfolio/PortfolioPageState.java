package de.axp.framework.example.vaadin.internal.pages.portfolio;

import static de.axp.framework.api.services.PortfolioService.PortfolioDefinition;

public final class PortfolioPageState {

	private PortfolioDefinition portfolioDefinition;
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
