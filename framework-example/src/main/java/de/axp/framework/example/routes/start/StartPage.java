package de.axp.framework.example.routes.start;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.api.services.TranslationService;
import de.axp.framework.example.ExampleTranslator;
import de.axp.framework.example.shared.links.PortfolioLink;

import java.util.Set;

import static de.axp.framework.api.services.PortfolioService.PortfolioDefinition;

@Route("")
@StyleSheet("frontend://styles/start.css")
public class StartPage extends Div {

	private static final String CLASS_NAME_EMPTY = "empty";
	private static final String CLASS_NAME_START = "start";
	private static final String CLASS_NAME_HEADER = "header";
	private static final String CLASS_NAME_PORTFOLIO_MENU = "portfolio-menu";

	private final PortfolioService portfolioService;
	private final TranslationService translationService;

	private final Section menuContainer;

	public StartPage() {
		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		portfolioService = framework.getServiceByType(PortfolioService.class);
		translationService = framework.getTranslationService();

		setClassName(CLASS_NAME_START);

		Div header = new Div();
		header.setClassName(CLASS_NAME_HEADER);
		add(header);

		menuContainer = new Section();
		menuContainer.setClassName(CLASS_NAME_PORTFOLIO_MENU);
		add(menuContainer);
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		Set<PortfolioDefinition> portfolioDefinitions = portfolioService.getPortfolioDefinitions();
		if (!portfolioDefinitions.isEmpty()) {
			fillMenu(portfolioDefinitions);
		} else {
			handleEmptyMenu();
		}
	}

	private void fillMenu(Set<PortfolioDefinition> portfolioDefinitions) {
		menuContainer.removeAll();
		menuContainer.removeClassName(CLASS_NAME_EMPTY);

		portfolioDefinitions.forEach(definition -> {
			PortfolioLink link = new PortfolioLink(definition);
			link.add((Component) definition.getPortfolioPreview().getUiComponent());
			menuContainer.add(link);
		});
	}

	private void handleEmptyMenu() {
		addClassName(CLASS_NAME_EMPTY);
		String translatorId = ExampleTranslator.class.getSimpleName();
		String translatedText = translationService.translate(translatorId, "no-portfolios-registered");
		setText(translatedText);
	}
}
