package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.UI;

import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;

class HomeButton extends SimpleNavigationButton {

	private static final long serialVersionUID = 2668356250682560949L;

	HomeButton(UrlState pageState) {
		super(pageState);
	}

	@Override
	String getImageLocation() {
		return "frontend/images/arrows_squares.svg";
	}

	@Override
	void handleClick() {
		UI.getCurrent().navigate("");
	}

	@Override
	void update() {
	}
}
