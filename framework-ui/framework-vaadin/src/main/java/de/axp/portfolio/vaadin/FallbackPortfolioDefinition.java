package de.axp.portfolio.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;

import java.util.ArrayList;
import java.util.List;

public class FallbackPortfolioDefinition implements UiService.PortfolioDefinition {

	private String text;

	public FallbackPortfolioDefinition(PortfolioFramework framework) {
		String translatorId = VaadinFrameworkTranslator.class.getSimpleName();
		text = framework.getTranslationService().translate(translatorId, "portfolio-not-available");
	}

	@Override
	public String getPortfolioId() {
		return "";
	}

	@Override
	public UiService.PortfolioPreview getPortfolioPreview() {
		return null;
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
			return new Label(text);
		}
	}
}
