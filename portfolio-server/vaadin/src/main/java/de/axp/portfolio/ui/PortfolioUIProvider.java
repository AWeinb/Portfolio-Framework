package de.axp.portfolio.ui;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

public class PortfolioUIProvider extends UIProvider {

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return PortfolioUI.class;
	}
}
