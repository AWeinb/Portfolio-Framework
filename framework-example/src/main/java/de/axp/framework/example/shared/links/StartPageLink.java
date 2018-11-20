package de.axp.framework.example.shared.links;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.RouterLink;

import de.axp.framework.example.routes.start.StartPage;

public class StartPageLink extends RouterLink {

	public StartPageLink() {
		super("", StartPage.class);

		setClassName("button");
		Image image = new Image("frontend/images/arrows_squares.svg", "");
		image.setClassName("icon");
		add(image);
	}
}
