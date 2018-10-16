package de.axp.portfolio.vaadin.internal.pages.shared;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.RouterLink;
import de.axp.portfolio.vaadin.internal.pages.start.StartPage;

public class StartPageSelector extends RouterLink {

	public StartPageSelector() {
		super("", StartPage.class);

		setClassName("button");
		Image image = new Image("frontend/images/arrows_squares.svg", "");
		image.setClassName("icon");
		add(image);
	}
}
