package de.axp.portfolio.vaadin.internal.pages.start;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.pages.shared.PortfolioSelector;

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

		if (portfolioDefinitions.isEmpty()) {
			addClassName("empty");
			setText("No Portfolios are registered.");
			return;
		}

		Section selectorContainer = new Section();
		selectorContainer.setClassName("selector");
		portfolioDefinitions.forEach(d -> addSelector(selectorContainer, d));
		add(selectorContainer);
	}

	private void addSelector(Section selectorContainer, UiService.PortfolioDefinition definition) {
		PortfolioSelector portfolioSelector = new PortfolioSelector(definition);
		portfolioSelector.add((Component) definition.getPortfolioPreview().getUiComponent());
		portfolioSelector.update();
		selectorContainer.add(portfolioSelector);
	}
}
