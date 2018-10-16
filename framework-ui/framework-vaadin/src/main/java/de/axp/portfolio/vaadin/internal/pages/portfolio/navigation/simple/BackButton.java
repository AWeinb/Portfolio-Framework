package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;

class BackButton extends Button implements SimpleNavigationButton {

	private static final long serialVersionUID = 4715771664378002357L;

	private final UrlState state;

	BackButton(UrlState state) {
		this.state = state;

		setClassName("button");
		setIcon(new Image("frontend/images/arrows_left_double.svg", ""));
		addClickListener(e -> handleClick());
	}

	private void handleClick() {
		int index = Math.max(state.getFirstPartIndex(), state.getPartIndex() - 1);
		String url = state.getRootSegment() + "/" + state.getPageSegment() + "/" + index;
		UI.getCurrent().navigate(url);
	}

	@Override
	public void update() {
		boolean isAtFirstPart = state.getFirstPartIndex() == state.getPartIndex();
		setEnabled(!isAtFirstPart);
	}
}
