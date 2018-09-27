package de.axp.portfolio.vaadin.internal.pages.portfolio.nav;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

abstract class NavigationButton extends Button {

	private static final long serialVersionUID = -3125822137857286516L;

	private final PortfolioPageState currentState;

	NavigationButton(PortfolioPageState currentState, Component icon) {
		super(icon);
		this.currentState = currentState;
		setClassName("button");
	}

	protected PortfolioPageState getPortfolioPageState() {
		return currentState;
	}
}
