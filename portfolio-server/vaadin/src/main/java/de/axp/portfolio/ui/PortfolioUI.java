package de.axp.portfolio.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class PortfolioUI extends UI {

	@Override
	protected void init(VaadinRequest request) {

		UiTestPresenter uiTestPresenter = new UiTestPresenter();
		uiTestPresenter.input();

		uiTestPresenter.update();
	}
}
