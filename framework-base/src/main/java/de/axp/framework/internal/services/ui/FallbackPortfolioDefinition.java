package de.axp.framework.internal.services.ui;

import java.util.ArrayList;
import java.util.List;

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

	public static class FallbackPortfolioPart implements UiService.PortfolioPart {

	}
}
