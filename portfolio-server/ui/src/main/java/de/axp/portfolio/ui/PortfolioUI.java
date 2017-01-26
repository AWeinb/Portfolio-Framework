package de.axp.portfolio.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.axp.portfolio.model.backend.BTestModel;
import de.axp.portfolio.model.ui.UTestModel;

public class PortfolioUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		BTestModel bTestModel = new BTestModel();

		UTestModel uTestModel = new UTestModel();
		uTestModel.setTest(bTestModel.getTest());

		Label label = new Label(uTestModel.getTest());
		layout.addComponent(label);

		setContent(layout);
	}
}
