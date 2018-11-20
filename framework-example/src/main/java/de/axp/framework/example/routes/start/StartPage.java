package de.axp.framework.example.routes.start;

import java.util.Set;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.TranslationService;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.example.shared.links.PortfolioLink;
import de.axp.framework.example.ExampleTranslator;

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
		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		PortfolioService portfolioService = framework.getServiceByType(PortfolioService.class);
		Set<PortfolioService.PortfolioDefinition> portfolioDefinitions = portfolioService.getPortfolioDefinitions();

		if (!portfolioDefinitions.isEmpty()) {
			fillMenu(portfolioDefinitions);
		} else {
			handleEmptyMenu(framework);
		}
	}

	private void fillMenu(Set<PortfolioService.PortfolioDefinition> portfolioDefinitions) {
		Section menuContainer = new Section();
		menuContainer.setClassName("portfolio-menu");
		for (PortfolioService.PortfolioDefinition d : portfolioDefinitions) {
			PortfolioLink link = new PortfolioLink(d);
			link.add((Component) d.getPortfolioPreview().getUiComponent());
			menuContainer.add(link);
		}
		add(menuContainer);
	}

	private void handleEmptyMenu(PortfolioFramework framework) {
		addClassName("empty");
		TranslationService translationService = framework.getTranslationService();
		String translatorId = ExampleTranslator.class.getSimpleName();
		setText(translationService.translate(translatorId, "no-portfolios-registered"));
	}
}
