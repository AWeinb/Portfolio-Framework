package de.axp.framework.example.routes.portfolio.navigation;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Nav;

@StyleSheet("frontend://styles/portfolio-navigation.css")
public class PortfolioNav extends Nav {

	public PortfolioNav() {
		setClassName("navigation-base");

		Div buttonBar = new Div();
		buttonBar.setClassName("button-bar");
		add(buttonBar);

		Div partSelector = new Div();
		partSelector.setClassName("part-selector");
		add(partSelector);
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
	}
}
