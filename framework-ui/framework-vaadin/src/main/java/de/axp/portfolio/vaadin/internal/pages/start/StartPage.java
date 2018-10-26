package de.axp.portfolio.vaadin.internal.pages.start;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.TranslationService;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.VaadinFrameworkTranslator;
import de.axp.portfolio.vaadin.internal.pages.shared.links.PortfolioLink;

import java.util.Set;

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
		UiService uiService = framework.getServiceByType(UiService.class);
		Set<UiService.PortfolioDefinition> portfolioDefinitions = uiService.getPortfolioDefinitions();

		if (!portfolioDefinitions.isEmpty()) {
			fillMenu(portfolioDefinitions);
		} else {
			handleEmptyMenu(framework);
		}
	}

	private void fillMenu(Set<UiService.PortfolioDefinition> portfolioDefinitions) {
		Section menuContainer = new Section();
		menuContainer.setClassName("portfolio-menu");
		for (UiService.PortfolioDefinition d : portfolioDefinitions) {
			PortfolioLink link = new PortfolioLink(d);
			link.add((Component) d.getPortfolioPreview().getUiComponent());
			menuContainer.add(link);
		}
		add(menuContainer);
	}

	private void handleEmptyMenu(PortfolioFramework framework) {
		addClassName("empty");
		TranslationService translationService = framework.getTranslationService();
		String translatorId = VaadinFrameworkTranslator.class.getSimpleName();
		setText(translationService.translate(translatorId, "no-portfolios-registered"));
	}
}
