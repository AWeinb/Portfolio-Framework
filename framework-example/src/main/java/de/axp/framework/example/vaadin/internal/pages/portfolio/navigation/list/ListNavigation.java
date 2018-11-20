package de.axp.framework.example.vaadin.internal.pages.portfolio.navigation.list;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;

import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.example.vaadin.internal.pages.portfolio.PortfolioPageState;
import de.axp.framework.example.vaadin.internal.pages.shared.links.NextPartLink;
import de.axp.framework.example.vaadin.internal.pages.shared.links.PortfolioLink;
import de.axp.framework.example.vaadin.internal.pages.portfolio.navigation.PortfolioNav;
import de.axp.framework.example.vaadin.internal.pages.shared.links.PreviousPartLink;
import de.axp.framework.example.vaadin.internal.pages.shared.links.StartPageLink;

@StyleSheet("frontend://styles/portfolio-navigation-list.css")
public final class ListNavigation extends PortfolioNav {

	private static final long serialVersionUID = -4212171098990270530L;

	private final PortfolioPageState state;

	private NextPartLink nextPartLink;
	private PreviousPartLink previousPartLink;
	private Div partsContainer;

	public ListNavigation(PortfolioPageState state) {
		this.state = state;
		setClassName("list-navigation");

		addDefaultMenu();
		addPartMenu();
	}

	private void addDefaultMenu() {
		Div buttonContainer = new Div();
		buttonContainer.setClassName("default-links");
		add(buttonContainer);

		StartPageLink startPageLink = new StartPageLink();
		nextPartLink = new NextPartLink(state);
		previousPartLink = new PreviousPartLink(state);
		buttonContainer.add(previousPartLink, startPageLink, nextPartLink);
	}

	private void addPartMenu() {
		partsContainer = new Div();
		partsContainer.setClassName("part-links");
		add(partsContainer);
	}

	@Override
	public void update() {
		nextPartLink.update();
		previousPartLink.update();

		partsContainer.removeAll();

		for (PortfolioService.PortfolioPart<?> part : state.getPortfolioDefinition().getPortfolioParts()) {
			PortfolioService.PortfolioDefinition portfolioDefinition = state.getPortfolioDefinition();
			PortfolioLink link = new PortfolioLink(portfolioDefinition, part);
			link.setText(part.getPartId());
			partsContainer.add(link);
		}
	}
}
