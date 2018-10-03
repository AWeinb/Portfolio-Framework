package de.axp.portfolio.vaadin.internal.pages.portfolio.nav;

import com.vaadin.flow.component.UI;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

class HomeButton extends NavigationButton {

	private static final long serialVersionUID = 2668356250682560949L;

	HomeButton(PortfolioPageState pageState) {
		super(pageState);
	}

	@Override
	String getImageLocation() {
		return "frontend/images/arrows_squares.svg";
	}

	@Override
	void handleClick() {
		UI.getCurrent().navigate("");
	}
}
