package de.axp.framework.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PortfolioService;

class ExamplePortfolioPartTwo implements PortfolioService.PortfolioPart<Component> {

	@Override
	public String getPartId() {
		return "part-two";
	}

	@Override
	public Component getUiComponent() {
		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		DataService dataService = framework.getDataService();
		return new Label((String) dataService.get("example-data"));
	}
}
