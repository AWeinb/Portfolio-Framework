package de.axp.portfolio.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PortfolioUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		addDetachListener(new UiDetachListener());

		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		Label label = new Label("test");
		layout.addComponent(label);

		setContent(layout);
	}
}
