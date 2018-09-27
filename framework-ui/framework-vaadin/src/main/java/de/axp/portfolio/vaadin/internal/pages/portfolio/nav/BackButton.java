package de.axp.portfolio.vaadin.internal.pages.portfolio.nav;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

class BackButton extends NavigationButton {

	private static final long serialVersionUID = 4715771664378002357L;

	BackButton(PortfolioPageState pageState) {
		super(pageState, new Icon(VaadinIcons.ARROW_LEFT));

		addClickListener(event -> {
			PortfolioPageState state = getPortfolioPageState();
			int index = state.getPreviousPartIndex();
			String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + index;
			UI.getCurrent().navigate(url);
		});
	}
}
