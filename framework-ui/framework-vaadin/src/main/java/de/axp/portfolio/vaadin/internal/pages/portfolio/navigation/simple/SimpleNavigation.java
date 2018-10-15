package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.dependency.StyleSheet;
import de.axp.portfolio.vaadin.internal.pages.portfolio.UrlState;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.PortfolioNavigationComponent;

@StyleSheet("frontend://styles/portfolio-simple-navigation.css")
public final class SimpleNavigation extends PortfolioNavigationComponent {

	private static final long serialVersionUID = -4212171098990270530L;

	private final HomeButton homeButton;
	private final ForwardButton forwardButton;
	private final BackButton backButton;

	public SimpleNavigation(UrlState state) {
		homeButton = new HomeButton(state);
		forwardButton = new ForwardButton(state);
		backButton = new BackButton(state);

		add(homeButton, forwardButton, backButton);

		setClassName("simple-navigation");
	}

	@Override
	public void update() {
		homeButton.update();
		forwardButton.update();
		backButton.update();
	}
}
