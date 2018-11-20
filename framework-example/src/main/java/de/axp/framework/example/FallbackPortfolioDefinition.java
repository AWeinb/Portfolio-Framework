package de.axp.framework.example;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.PortfolioService;

public class FallbackPortfolioDefinition implements PortfolioService.PortfolioDefinition {

	private String text;

	public FallbackPortfolioDefinition(PortfolioFramework framework) {
		String translatorId = ExampleTranslator.class.getSimpleName();
		text = framework.getTranslationService().translate(translatorId, "portfolio-not-available");
	}

	@Override
	public String getPortfolioId() {
		return "";
	}

	@Override
	public PortfolioService.PortfolioPreview getPortfolioPreview() {
		return null;
	}

	@Override
	public List<? extends PortfolioService.PortfolioPart> getPortfolioParts() {
		List<FallbackPortfolioPart> parts = new ArrayList<>();
		parts.add(new FallbackPortfolioPart());
		return parts;
	}

	class FallbackPortfolioPart implements PortfolioService.PortfolioPart {

		@Override
		public String getPartId() {
			return "";
		}

		@Override
		public Component getUiComponent() {
			return new Label(text);
		}
	}
}
