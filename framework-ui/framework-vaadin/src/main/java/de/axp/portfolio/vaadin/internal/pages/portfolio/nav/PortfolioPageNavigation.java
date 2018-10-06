package de.axp.portfolio.vaadin.internal.pages.portfolio.nav;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.html.Nav;

import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

public class PortfolioPageNavigation extends Nav {

	private static final long serialVersionUID = -4212171098990270530L;

	private final Map<Type, NavigationButton> buttons = new HashMap<>();
	private final PortfolioPageState currentState;

	public PortfolioPageNavigation(PortfolioPageState currentState) {
		this.currentState = currentState;

		setClassName("navigation");

		buttons.put(Type.HOME, new HomeButton(currentState));
		buttons.put(Type.FORWARD, new ForwardButton(currentState));
		buttons.put(Type.BACK, new BackButton(currentState));

		for (Type type : Type.values()) {
			add(buttons.get(type));
		}
	}

	public void update() {
		activateButton(Type.FORWARD);
		activateButton(Type.BACK);

		if (currentState.getPageIndex() == currentState.getNextPartIndex()) {
			disableButton(Type.FORWARD);
		}
		if (currentState.getPageIndex() == currentState.getPreviousPartIndex()) {
			disableButton(Type.BACK);
		}
	}

	private void activateButton(Type type) {
		buttons.get(type).setEnabled(true);
	}

	private void disableButton(Type type) {
		buttons.get(type).setEnabled(false);
	}

	public enum Type {
		HOME, FORWARD, BACK
	}
}
