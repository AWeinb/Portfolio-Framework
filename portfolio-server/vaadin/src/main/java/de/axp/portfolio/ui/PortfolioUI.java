package de.axp.portfolio.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class PortfolioUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout content = new VerticalLayout();
		content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		content.setSizeFull();

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		content.addComponent(horizontalLayout);

		Label label = new Label("Hi!");
		horizontalLayout.addComponent(label);

		setContent(content);
	}
}
