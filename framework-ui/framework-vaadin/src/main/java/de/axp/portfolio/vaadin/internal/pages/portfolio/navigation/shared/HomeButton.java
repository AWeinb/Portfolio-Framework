package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.shared;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;

public class HomeButton extends Button implements NavigationButton {

	private static final long serialVersionUID = 2668356250682560949L;

	public HomeButton() {
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
