package de.axp.portfolio.example.vaadin.internal.pages.shared.links;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.router.RouterLink;

import de.axp.framework.api.services.PortfolioService;
import de.axp.portfolio.example.vaadin.internal.pages.portfolio.PortfolioPage;
import de.axp.portfolio.example.vaadin.internal.pages.shared.QueryParametersUtil;

abstract class PortfolioRouterLink extends RouterLink {

	private static final long serialVersionUID = -8770716265270100412L;

	private final Map<String, List<String>> queryParametersMap = new HashMap<>(2);

	protected PortfolioRouterLink() {
		super("", PortfolioPage.class);
		setClassName("button");
	}

	public void update() {
		PortfolioService.PortfolioDefinition portfolioDefinition = getPortfolioDefinitionTarget();
		PortfolioService.PortfolioPart<?> portfolioPart = getPortfolioPartTarget();
		setQueryParameters(QueryParametersUtil.build(queryParametersMap, portfolioDefinition, portfolioPart));
	}

	protected abstract PortfolioService.PortfolioDefinition getPortfolioDefinitionTarget();

	protected abstract PortfolioService.PortfolioPart<?> getPortfolioPartTarget();
}
