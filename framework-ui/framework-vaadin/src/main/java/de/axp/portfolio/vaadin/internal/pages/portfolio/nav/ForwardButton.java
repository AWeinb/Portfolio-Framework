package de.axp.portfolio.vaadin.internal.pages.portfolio.nav;

import com.vaadin.flow.component.UI;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

class ForwardButton extends NavigationButton {

	private static final long serialVersionUID = -1787353288877153330L;

	ForwardButton(PortfolioPageState pageState) {
		super(pageState);
	}

	@Override
	String getImageLocation() {
		return "frontend/images/arrows_right_double.svg";
	}

	@Override
	void handleClick() {
		PortfolioPageState state = getPortfolioPageState();
		String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + state.getNextPartIndex();
		UI.getCurrent().navigate(url);
	}
}
