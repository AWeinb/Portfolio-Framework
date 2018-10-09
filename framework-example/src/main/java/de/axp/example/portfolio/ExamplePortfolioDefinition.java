package de.axp.example.portfolio;

import de.axp.framework.api.services.UiService;

import java.util.ArrayList;
import java.util.List;

public class ExamplePortfolioDefinition implements UiService.PortfolioDefinition {

	@Override
	public String getPortfolioId() {
		return "example";
	}

	@Override
	public UiService.PortfolioPreview getPortfolioPreview() {
		return new ExamplePortfolioPreview();
	}

	@Override
	public List<? extends UiService.PortfolioPart> getPortfolioParts() {
		ArrayList<UiService.PortfolioPart> parts = new ArrayList<>();
		parts.add(new ExamplePortfolioPartOne());
		parts.add(new ExamplePortfolioPartTwo());
		return parts;
	}
}
