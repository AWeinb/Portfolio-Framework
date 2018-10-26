package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.list;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.PortfolioNav;
import de.axp.portfolio.vaadin.internal.pages.shared.links.PortfolioBackSelector;
import de.axp.portfolio.vaadin.internal.pages.shared.links.PortfolioForwardSelector;
import de.axp.portfolio.vaadin.internal.pages.shared.links.PortfolioSelector;
import de.axp.portfolio.vaadin.internal.pages.shared.links.StartPageSelector;

@StyleSheet("frontend://styles/portfolio-list-navigation.css")
public final class ListNavigation extends PortfolioNav {

	private static final long serialVersionUID = -4212171098990270530L;

	private final PortfolioPageState state;

	private PortfolioForwardSelector forwardButton;
	private PortfolioBackSelector backButton;
	private Div partSelectorContainer;

	public ListNavigation(PortfolioPageState state) {
		this.state = state;
		setClassName("list-navigation");

		addDefaultMenu();
		addPartMenu();
	}

	private void addDefaultMenu() {
		Div buttonContainer = new Div();
		buttonContainer.setClassName("default-btns");
		add(buttonContainer);

		StartPageSelector menuButton = new StartPageSelector();
		forwardButton = new PortfolioForwardSelector(state);
		backButton = new PortfolioBackSelector(state);
		buttonContainer.add(backButton, menuButton, forwardButton);
	}

	private void addPartMenu() {
		partSelectorContainer = new Div();
		partSelectorContainer.setClassName("parts");
		add(partSelectorContainer);
	}

	@Override
	public void update() {
		forwardButton.update();
		backButton.update();

		partSelectorContainer.removeAll();

		for (UiService.PortfolioPart<?> part : state.getPortfolioDefinition().getPortfolioParts()) {
			UiService.PortfolioDefinition portfolioDefinition = state.getPortfolioDefinition();
			PortfolioSelector portfolioSelector = new PortfolioSelector(portfolioDefinition, part);
			portfolioSelector.setText(part.getPartId());
			partSelectorContainer.add(portfolioSelector);
		}
	}
}
