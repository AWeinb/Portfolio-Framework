package de.axp.portfolio.vaadin.internal.pages.shared.links;

import static de.axp.framework.api.services.UiService.PortfolioDefinition;
import static de.axp.framework.api.services.UiService.PortfolioPart;

public class PortfolioLink extends PortfolioRouterLink {

	private static final long serialVersionUID = -8816745656322222870L;

	private final PortfolioDefinition portfolioDefinition;
	private final PortfolioPart<?> portfolioPart;

	public PortfolioLink(PortfolioDefinition portfolioDefinition) {
		this(portfolioDefinition, portfolioDefinition.getPortfolioParts().get(0));
	}

	public PortfolioLink(PortfolioDefinition portfolioDefinition, PortfolioPart<?> portfolioPart) {
		this.portfolioDefinition = portfolioDefinition;
		this.portfolioPart = portfolioPart;

		update();
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
