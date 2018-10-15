package de.axp.portfolio.vaadin.internal.pages.portfolio.content;

import com.vaadin.flow.component.Component;
import de.axp.framework.api.services.UiService;
import de.axp.framework.api.services.UiService.PortfolioDefinition;
import de.axp.framework.api.services.UiService.PortfolioPart;
import de.axp.portfolio.vaadin.internal.pages.portfolio.FallbackPortfolioDefinition;
import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;
import de.axp.portfolio.vaadin.internal.pages.portfolio.content.simple.SimpleContentLayout;

import java.util.List;
import java.util.Optional;

public class PortfolioContentManager {

	private final UiService uiService;
	private final UrlState urlState;
	private final SimpleContentLayout simpleContentLayout = new SimpleContentLayout();

	public PortfolioContentManager(UiService uiService, UrlState urlState) {
		this.uiService = uiService;
		this.urlState = urlState;
	}

	public PortfolioContentComponent getContentComponent() {
		return simpleContentLayout;
	}

	public void updateContentComponent() {
		simpleContentLayout.removeAll();
		simpleContentLayout.add(getPortfolioPartComponent());
	}

	private Component getPortfolioPartComponent() {
		String pageSegment = urlState.getPageSegment();
		Optional<PortfolioDefinition> portfolioDefinition = uiService.getPortfolioDefinition(pageSegment);
		PortfolioDefinition currentDefinition = portfolioDefinition.orElse(new FallbackPortfolioDefinition());
		List<? extends PortfolioPart> portfolioParts = currentDefinition.getPortfolioParts();
		PortfolioPart<Component> portfolioPart = portfolioParts.get(urlState.getPartIndex());
		return portfolioPart.getUiComponent();
	}
}
