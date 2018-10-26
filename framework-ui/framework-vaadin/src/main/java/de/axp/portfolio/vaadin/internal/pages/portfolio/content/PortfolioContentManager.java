package de.axp.portfolio.vaadin.internal.pages.portfolio.content;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import de.axp.framework.api.services.UiService;
import de.axp.framework.api.services.UiService.PortfolioPart;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;
import de.axp.portfolio.vaadin.internal.pages.portfolio.content.simple.SimpleContentLayout;

public class PortfolioContentManager {

	private final PortfolioPageState pageState;
	private final Div contentBase = new Div();
	private final SimpleContentLayout simpleContentLayout = new SimpleContentLayout();

	public PortfolioContentManager(PortfolioPageState pageState) {
		this.pageState = pageState;

		contentBase.setClassName("content-base");
		contentBase.add(simpleContentLayout);
	}

	public Component getContentComponent() {
		return contentBase;
	}

	@SuppressWarnings("unchecked")
	public void update() {
		simpleContentLayout.removeAll();

		UiService.PortfolioDefinition portfolioDefinition = pageState.getPortfolioDefinition();
		int portfolioPartIndex = pageState.getPortfolioPartIndex();
		PortfolioPart<Component> part = portfolioDefinition.getPortfolioParts().get(portfolioPartIndex);
		simpleContentLayout.add(part.getUiComponent());
	}
}
