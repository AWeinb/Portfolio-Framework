package de.axp.portfolio.vaadin.internal.services.pages;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import de.axp.framework.api.PortfolioFramework;
import de.axp.portfolio.vaadin.api.plugins.PortfolioDefinition;
import de.axp.portfolio.vaadin.api.services.UiService;
import de.axp.portfolio.vaadin.internal.services.pages.portfolio.PortfolioPage;

@Route("")
@StyleSheet("frontend://styles/startpage.css")
public class StartPage extends Div {

	private static final long serialVersionUID = -2132241652252682848L;
	private static final String BUTTON_CLASSNAME = "button";

	private final Section selector = new Section();

	{
		setClassName("pf-startpage");
		selector.setClassName("selector");
		initContent();
	}

	private void initContent() {
		add(selector);

		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		UiService uiService = framework.getServiceByType(UiService.class);
		uiService.getPortfolioDefinitions().forEach(this::addSelector);
	}

	private void addSelector(PortfolioDefinition definition) {
		RouterLink routerLink = new RouterLink("", PortfolioPage.class, definition.getPortfolioId());
		routerLink.setClassName(BUTTON_CLASSNAME);
		selector.add(routerLink);
	}
}
