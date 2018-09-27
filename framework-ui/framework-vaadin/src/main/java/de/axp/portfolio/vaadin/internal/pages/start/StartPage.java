package de.axp.portfolio.vaadin.internal.pages.start;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPage;

import java.util.Set;

@Route("")
@StyleSheet("frontend://styles/startpage.css")
public class StartPage extends Div {

	private static final long serialVersionUID = -2132241652252682848L;

	{
		setClassName("pf-startpage");
		initContent();
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
