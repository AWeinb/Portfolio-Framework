package de.axp.portfolio.vaadin.internal.services;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;

import de.axp.portfolio.vaadin.api.services.UiService;

class FallbackPortfolioDefinition implements UiService.PortfolioDefinition {

	@Override
	public String getPortfolioId() {
		return "";
	}

	@Override
	public List<Class<? extends Component>> getPortfolioParts() {
		List<Class<? extends Component>> parts = new ArrayList<>();
		parts.add(FallbackPortfolioPart.class);
		return parts;
	}

	public static class FallbackPortfolioPart extends Label {

		private static final long serialVersionUID = -3248313044660217309L;

		@SuppressWarnings("CheckStyle")
		public FallbackPortfolioPart() {
			setText("Upps! There is nothing like that!");
		}
	}
}
