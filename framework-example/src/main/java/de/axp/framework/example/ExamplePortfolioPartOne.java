package de.axp.framework.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import de.axp.framework.api.services.PortfolioService;

class ExamplePortfolioPartOne implements PortfolioService.PortfolioPart<Component> {

	@Override
	public String getPartId() {
		return "part-one";
	}

	@Override
	public Component getUiComponent() {
		return new Label("1111");
	}
}
