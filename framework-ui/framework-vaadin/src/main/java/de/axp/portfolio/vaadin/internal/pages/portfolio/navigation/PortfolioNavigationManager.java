package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation;

import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple.SimpleNavigation;

public class PortfolioNavigationManager {

	private final SimpleNavigation simpleNavigation;

	public PortfolioNavigationManager(UrlState urlState) {
		this.simpleNavigation = new SimpleNavigation(urlState);
	}

	public PortfolioNavigationComponent getNavigationComponent() {
		return simpleNavigation;
	}

	public void updateNavigationComponent() {
		simpleNavigation.update();
	}
}
