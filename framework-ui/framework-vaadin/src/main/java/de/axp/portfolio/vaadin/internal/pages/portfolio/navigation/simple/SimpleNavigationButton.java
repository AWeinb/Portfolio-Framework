package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;

import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;

abstract class SimpleNavigationButton extends Button {

	private static final long serialVersionUID = -3125822137857286516L;

	private final UrlState currentState;

	SimpleNavigationButton(UrlState currentState) {
		this.currentState = currentState;

		setClassName("button");
		setIcon(new Image(getImageLocation(), ""));
		addClickListener(e -> handleClick());
	}

	UrlState getPageState() {
		return currentState;
	}

	abstract String getImageLocation();

	abstract void handleClick();

	abstract void update();
}
