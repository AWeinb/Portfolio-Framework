package de.axp.framework.example.vaadin.internal.pages.portfolio.content;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Section;

import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.api.services.PortfolioService.PortfolioPart;
import de.axp.framework.example.vaadin.internal.pages.portfolio.PortfolioPageState;

@StyleSheet("frontend://styles/portfolio-content.css")
class PortfolioContent extends Section {

	private static final long serialVersionUID = 1482215989642569717L;

	private final PortfolioPageState pageState;

	PortfolioContent(PortfolioPageState pageState) {
		this.pageState = pageState;
		setClassName("content");
	}

	void update() {
		removeAll();

		PortfolioService.PortfolioDefinition portfolioDefinition = pageState.getPortfolioDefinition();
		int portfolioPartIndex = pageState.getPortfolioPartIndex();
		PortfolioPart<?> part = portfolioDefinition.getPortfolioParts().get(portfolioPartIndex);
		if (part.getUiComponent() instanceof Component) {
			add((Component) part.getUiComponent());
		}
	}
}
