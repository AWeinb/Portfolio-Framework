package de.axp.portfolio.vaadin.internal.pages.portfolio.nav;

import com.vaadin.flow.component.UI;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

class BackButton extends NavigationButton {

	private static final long serialVersionUID = 4715771664378002357L;

	BackButton(PortfolioPageState pageState) {
		super(pageState);
	}

	@Override
	String getImageLocation() {
		return "frontend/images/arrows_left_double.svg";
	}

	@Override
	void handleClick() {
		PortfolioPageState state = getPortfolioPageState();
		int index = state.getPreviousPartIndex();
		String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + index;
		UI.getCurrent().navigate(url);
	}
}
