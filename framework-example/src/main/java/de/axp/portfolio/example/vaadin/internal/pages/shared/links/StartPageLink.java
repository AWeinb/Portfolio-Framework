package de.axp.portfolio.example.vaadin.internal.pages.shared.links;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.RouterLink;

import de.axp.portfolio.example.vaadin.internal.pages.start.StartPage;

public class StartPageLink extends RouterLink {

	public StartPageLink() {
		super("", StartPage.class);

		setClassName("button");
		Image image = new Image("frontend/images/arrows_squares.svg", "");
		image.setClassName("icon");
		add(image);
	}
}
