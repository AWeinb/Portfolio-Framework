package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;

class HomeButton extends Button implements SimpleNavigationButton {

	private static final long serialVersionUID = 2668356250682560949L;

	HomeButton() {
		setClassName("button");
		setIcon(new Image("frontend/images/arrows_squares.svg", ""));
		addClickListener(e -> handleClick());
	}

	private void handleClick() {
		UI.getCurrent().navigate("");
	}

	@Override
	public void update() {

	}
}
