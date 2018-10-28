package de.axp.portfolio.vaadin.internal.pages.portfolio.content;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

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
