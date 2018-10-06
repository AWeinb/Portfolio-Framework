package de.axp.portfolio.vaadin.internal.pages.start;

import java.util.Set;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPage;

@Route("")
@StyleSheet("frontend://styles/start.css")
public class StartPage extends Div {

	private static final long serialVersionUID = -2132241652252682848L;

	{
		setClassName("start");
		initHeader();
		initContent();
	}

	private void initHeader() {
		Div header = new Div();
		header.setClassName("header");
		add(header);
	}

	private void initContent() {
		Section selectorContainer = new Section();
		selectorContainer.setClassName("selector");

		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		UiService uiService = framework.getServiceByType(UiService.class);
		Set<UiService.PortfolioDefinition> portfolioDefinitions = uiService.getPortfolioDefinitions();
		portfolioDefinitions.forEach(d -> addSelector(selectorContainer, d));

		if (portfolioDefinitions.isEmpty()) {
			addClassName("empty");
			setText("No Portfolios are registered.");
		} else {
			add(selectorContainer);
		}
	}

	private void addSelector(Section selectorContainer, UiService.PortfolioDefinition definition) {
		RouterLink routerLink = new RouterLink("", PortfolioPage.class, definition.getPortfolioId());
		routerLink.setClassName("button");
		selectorContainer.add(routerLink);
	}
}
