package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.UI;

import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;

class ForwardButton extends SimpleNavigationButton {

	private static final long serialVersionUID = -1787353288877153330L;

	ForwardButton(UrlState pageState) {
		super(pageState);
	}

	@Override
	String getImageLocation() {
		return "frontend/images/arrows_right_double.svg";
	}

	@Override
	void handleClick() {
		UrlState state = getPageState();
		int index = Math.min(state.getLastPartIndex(), state.getPartIndex() + 1);
		String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + index;
		UI.getCurrent().navigate(url);
	}

	@Override
	void update() {
		UrlState state = getPageState();
		boolean isAtLastPart = state.getLastPartIndex() == state.getPartIndex();
		setEnabled(!isAtLastPart);
	}
}
