package de.axp.framework.example.routes.portfolio.navigation;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

import de.axp.framework.example.routes.portfolio.PortfolioPageState;
import de.axp.framework.example.routes.portfolio.navigation.list.ListNavigation;
import de.axp.framework.example.routes.portfolio.navigation.simple.SimpleNavigation;

public class PortfolioNavigationManager {

	private final Map<NavigationType, PortfolioNav> navs = new HashMap<>();
	private final Div container = new Div();

	private NavigationType navigationType = NavigationType.SIMPLE;

	public PortfolioNavigationManager(PortfolioPageState pageState) {
		navs.put(NavigationType.SIMPLE, new SimpleNavigation(pageState));
		navs.put(NavigationType.LIST, new ListNavigation(pageState));

		Button navigationToggleButton = createToggleButton();
		container.add(navigationToggleButton);

		container.add(navs.get(navigationType));
		container.setClassName("navigation-base");
	}

	public Component getNavigationComponent() {
		return container;
	}

	public void update() {
		navs.get(navigationType).update();
	}

	private Button createToggleButton() {
		Button button = new Button();
		button.setClassName("button");
		button.addClassName("navigation-toggle-btn");
		button.setIcon(new Image("frontend/images/arrows_switch_horizontal.svg", ""));
		button.addClickListener(e -> {
			switchToNextNavigationComponent();
			update();
		});
		return button;
	}

	private void switchToNextNavigationComponent() {
		container.remove(navs.get(navigationType));
		navigationType = navigationType.getNext();
		container.add(navs.get(navigationType));
	}
}
