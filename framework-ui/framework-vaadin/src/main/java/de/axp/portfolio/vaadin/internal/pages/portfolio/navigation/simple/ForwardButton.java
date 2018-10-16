package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;

class ForwardButton extends Button implements SimpleNavigationButton {

	private static final long serialVersionUID = -1787353288877153330L;

	private final UrlState state;

	ForwardButton(UrlState state) {
		this.state = state;

		setClassName("button");
		setIcon(new Image("frontend/images/arrows_right_double.svg", ""));
		addClickListener(e -> handleClick());
	}

	private void handleClick() {
		int index = Math.min(state.getLastPartIndex(), state.getPartIndex() + 1);
		String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + index;
		UI.getCurrent().navigate(url);
	}

	@Override
	public void update() {
		boolean isAtLastPart = state.getLastPartIndex() == state.getPartIndex();
		setEnabled(!isAtLastPart);
	}
}
