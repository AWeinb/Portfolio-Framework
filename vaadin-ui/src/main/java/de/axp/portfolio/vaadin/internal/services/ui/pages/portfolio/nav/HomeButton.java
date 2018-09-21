package de.axp.portfolio.vaadin.internal.services.ui.pages.portfolio.nav;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;

import de.axp.portfolio.vaadin.internal.services.ui.pages.portfolio.PortfolioPageState;

class HomeButton extends NavigationButton {

	private static final long serialVersionUID = 2668356250682560949L;

	HomeButton(PortfolioPageState pageState) {
		super(pageState, new Icon(VaadinIcons.HOME));

		addClickListener(event -> UI.getCurrent().navigate(""));
	}
}
