package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.dependency.StyleSheet;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.PortfolioNav;
import de.axp.portfolio.vaadin.internal.pages.shared.PortfolioBackSelector;
import de.axp.portfolio.vaadin.internal.pages.shared.PortfolioForwardSelector;
import de.axp.portfolio.vaadin.internal.pages.shared.StartPageSelector;

@StyleSheet("frontend://styles/portfolio-simple-navigation.css")
public final class SimpleNavigation extends PortfolioNav {

	private static final long serialVersionUID = -4212171098990270530L;

	private final PortfolioForwardSelector forwardButton;
	private final PortfolioBackSelector backButton;

	public SimpleNavigation(PortfolioPageState state) {
		StartPageSelector menuButton = new StartPageSelector();
		forwardButton = new PortfolioForwardSelector(state);
		backButton = new PortfolioBackSelector(state);

		add(menuButton, forwardButton, backButton);

		setClassName("simple-navigation");
	}

	@Override
	public void update() {
		forwardButton.update();
		backButton.update();
	}
}
