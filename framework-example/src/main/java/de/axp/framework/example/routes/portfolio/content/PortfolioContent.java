package de.axp.framework.example.routes.portfolio.content;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Section;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.api.services.PortfolioService.PortfolioPart;
import de.axp.framework.example.routes.portfolio.PortfolioPageState;

@StyleSheet("frontend://styles/portfolio-content.css")
public class PortfolioContent extends Section {

	private final PortfolioPageState pageState;

	public PortfolioContent(PortfolioPageState pageState) {
		this.pageState = pageState;

		setClassName("content");
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		removeAll();

		PortfolioService.PortfolioDefinition portfolioDefinition = pageState.getPortfolioDefinition();
		int portfolioPartIndex = pageState.getPortfolioPartIndex();
		PortfolioPart<?> part = portfolioDefinition.getPortfolioParts().get(portfolioPartIndex);
		if (part.getUiComponent() instanceof Component) {
			add((Component) part.getUiComponent());
		}
	}
}
