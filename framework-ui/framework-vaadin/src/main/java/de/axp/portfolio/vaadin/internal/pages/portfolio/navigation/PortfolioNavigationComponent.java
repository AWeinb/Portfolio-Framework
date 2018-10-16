package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Nav;

@StyleSheet("frontend://styles/portfolio-navigation.css")
public abstract class PortfolioNavigationComponent extends Nav {

	private static final long serialVersionUID = 1614357453838016041L;

	public abstract void update();
}
