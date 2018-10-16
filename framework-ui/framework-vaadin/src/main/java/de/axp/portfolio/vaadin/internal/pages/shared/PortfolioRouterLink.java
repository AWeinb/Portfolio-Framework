package de.axp.portfolio.vaadin.internal.pages.shared;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.router.RouterLink;

import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.pages.QueryParametersUtil;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPage;

abstract class PortfolioRouterLink extends RouterLink {

	private static final long serialVersionUID = -8770716265270100412L;

	private final Map<String, List<String>> queryParametersMap = new HashMap<>(2);

	protected PortfolioRouterLink() {
		super("", PortfolioPage.class);
		setClassName("button");
	}

	public void update() {
		UiService.PortfolioDefinition portfolioDefinition = getPortfolioDefinitionTarget();
		UiService.PortfolioPart<?> portfolioPart = getPortfolioPartTarget();
		setQueryParameters(QueryParametersUtil.build(queryParametersMap, portfolioDefinition, portfolioPart));
	}

	protected abstract UiService.PortfolioDefinition getPortfolioDefinitionTarget();

	protected abstract UiService.PortfolioPart<?> getPortfolioPartTarget();
}
