
package de.axp.portfolio.vaadin.internal.pages.portfolio;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.Label;

import de.axp.framework.api.services.UiService;

class FallbackPortfolioDefinition implements UiService.PortfolioDefinition {

	@Override
	public String getPortfolioId() {
		return "";
	}

	@Override
	public List<Class<? extends UiService.PortfolioPart>> getPortfolioParts() {
		List<Class<? extends UiService.PortfolioPart>> parts = new ArrayList<>();
		parts.add(FallbackPortfolioPart.class);
		return parts;
	}

	public class FallbackPortfolioPart extends Label implements UiService.PortfolioPart {

		private static final long serialVersionUID = -9156630648629447374L;

		public FallbackPortfolioPart() {
			setText("Upps! There is nothing like that!");
		}

		@Override
		public String getPartId() {
			return "";
		}
	}
}
