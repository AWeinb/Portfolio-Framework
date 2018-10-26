package de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.simple;

import com.vaadin.flow.component.dependency.StyleSheet;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.PortfolioNav;
import de.axp.portfolio.vaadin.internal.pages.shared.links.PreviousPartLink;
import de.axp.portfolio.vaadin.internal.pages.shared.links.NextPartLink;
import de.axp.portfolio.vaadin.internal.pages.shared.links.StartPageLink;

@StyleSheet("frontend://styles/portfolio-navigation-simple.css")
public final class SimpleNavigation extends PortfolioNav {

	private static final long serialVersionUID = -4212171098990270530L;

	private final NextPartLink nextPartLink;
	private final PreviousPartLink previousPartLink;

	public SimpleNavigation(PortfolioPageState state) {
		StartPageLink startPageLink = new StartPageLink();
		nextPartLink = new NextPartLink(state);
		previousPartLink = new PreviousPartLink(state);

		add(startPageLink, nextPartLink, previousPartLink);

		setClassName("simple-navigation");
	}

	@Override
	public void update() {
		nextPartLink.update();
		previousPartLink.update();
	}
}
