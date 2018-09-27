package de.axp.example.portfolio;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import de.axp.framework.api.services.UiService;

class ExamplePortfolioPartTwo implements UiService.PortfolioPart<Component> {

	@Override
	public String getPartId() {
		return "part-two";
	}

	@Override
	public Component getUiComponent() {
		return new Label("2222");
	}
}
