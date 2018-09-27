package de.axp.portfolio.vaadin.internal.pages.portfolio;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import de.axp.framework.api.services.UiService;

import java.util.ArrayList;
import java.util.List;

public class FallbackPortfolioDefinition implements UiService.PortfolioDefinition {

	@Override
	public String getPortfolioId() {
		return "";
	}

	@Override
	public List<? extends UiService.PortfolioPart> getPortfolioParts() {
		List<FallbackPortfolioPart> parts = new ArrayList<>();
		parts.add(new FallbackPortfolioPart());
		return parts;
	}

	class FallbackPortfolioPart implements UiService.PortfolioPart {

		@Override
		public String getPartId() {
			return "";
		}

		@Override
		public Component getUiComponent() {
			return new Label("Upps! There is nothing like that!");
		}
	}
}
