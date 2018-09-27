package de.axp.example.portfolio;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import de.axp.framework.api.services.UiService;

class ExamplePortfolioPartOne implements UiService.PortfolioPart<Component> {

	@Override
	public String getPartId() {
		return "part-one";
	}

	@Override
	public Component getUiComponent() {
		return new Label("1111");
	}
}
