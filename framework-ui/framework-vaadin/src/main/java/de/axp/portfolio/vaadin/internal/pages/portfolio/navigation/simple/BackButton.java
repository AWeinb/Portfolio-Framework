package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.UI;

import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;

class BackButton extends SimpleNavigationButton {

	private static final long serialVersionUID = 4715771664378002357L;

	BackButton(UrlState pageState) {
		super(pageState);
	}

	@Override
	String getImageLocation() {
		return "frontend/images/arrows_left_double.svg";
	}

	@Override
	void handleClick() {
		UrlState state = getPageState();
		int index = Math.max(state.getFirstPartIndex(), state.getPartIndex() - 1);
		String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + index;
		UI.getCurrent().navigate(url);
	}

	@Override
	void update() {
		UrlState state = getPageState();
		boolean isAtFirstPart = state.getFirstPartIndex() == state.getPartIndex();
		setEnabled(!isAtFirstPart);
	}
}
