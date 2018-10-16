package de.axp.portfolio.vaadin.internal.pages.shared;

import static de.axp.framework.api.services.UiService.PortfolioDefinition;
import static de.axp.framework.api.services.UiService.PortfolioPart;

public class PortfolioSelector extends PortfolioRouterLink {

	private static final long serialVersionUID = -8816745656322222870L;

	private final PortfolioDefinition portfolioDefinition;
	private final PortfolioPart<?> portfolioPart;

	public PortfolioSelector(PortfolioDefinition portfolioDefinition) {
		this(portfolioDefinition, portfolioDefinition.getPortfolioParts().get(0));
	}

	public PortfolioSelector(PortfolioDefinition portfolioDefinition, PortfolioPart<?> portfolioPart) {
		this.portfolioDefinition = portfolioDefinition;
		this.portfolioPart = portfolioPart;
	}

	@Override
	protected PortfolioDefinition getPortfolioDefinitionTarget() {
		return portfolioDefinition;
	}

	@Override
	protected PortfolioPart<?> getPortfolioPartTarget() {
		return portfolioPart;
	}
}
