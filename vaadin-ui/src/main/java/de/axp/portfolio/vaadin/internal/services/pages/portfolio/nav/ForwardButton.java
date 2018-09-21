package de.axp.portfolio.vaadin.internal.services.pages.portfolio.nav;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;

import de.axp.portfolio.vaadin.internal.services.pages.portfolio.PortfolioPageState;

class ForwardButton extends NavigationButton {

	private static final long serialVersionUID = -1787353288877153330L;

	ForwardButton(PortfolioPageState pageState) {
		super(pageState, new Icon(VaadinIcons.ARROW_RIGHT));

		addClickListener(event -> {
			PortfolioPageState state = getPortfolioPageState();
			String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + state.getNextPartIndex();
			UI.getCurrent().navigate(url);
		});
	}
}
