package de.axp.framework.example.routes.portfolio.content;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import de.axp.framework.example.routes.portfolio.PortfolioPageState;

public class PortfolioContentManager {

	private final Div contentBase = new Div();
	private final PortfolioContent portfolioContent;

	public PortfolioContentManager(PortfolioPageState pageState) {
		portfolioContent = new PortfolioContent(pageState);

		contentBase.setClassName("content-base");
		contentBase.add(portfolioContent);
	}

	public Component getContentComponent() {
		return contentBase;
	}

	public void update() {
		portfolioContent.update();
	}
}
