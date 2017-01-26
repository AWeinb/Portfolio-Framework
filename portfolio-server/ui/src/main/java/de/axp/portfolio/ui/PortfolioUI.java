package de.axp.portfolio.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.axp.portfolio.model.backend.TestModel;

public class PortfolioUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		TestModel testModel = new TestModel();

		Label label = new Label(testModel.getTest());
		layout.addComponent(label);

		setContent(layout);
	}
}
