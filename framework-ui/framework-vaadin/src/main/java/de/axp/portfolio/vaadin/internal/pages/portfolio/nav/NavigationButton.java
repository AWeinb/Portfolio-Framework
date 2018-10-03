package de.axp.portfolio.vaadin.internal.pages.portfolio.nav;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

abstract class NavigationButton extends Button {

	private static final long serialVersionUID = -3125822137857286516L;

	private final PortfolioPageState currentState;

	NavigationButton(PortfolioPageState currentState) {
		this.currentState = currentState;

		setClassName("button");
		setIcon(new Image(getImageLocation(), ""));
		addClickListener(e -> handleClick());
	}

	PortfolioPageState getPortfolioPageState() {
		return currentState;
	}

	abstract String getImageLocation();

	abstract void handleClick();
}
