package de.axp.framework.example;

import de.axp.framework.api.services.PortfolioService;

import java.util.ArrayList;
import java.util.List;

public class ExamplePortfolioDefinition implements PortfolioService.PortfolioDefinition {

	@Override
	public String getPortfolioId() {
		return "example";
	}

	@Override
	public PortfolioService.PortfolioPreview getPortfolioPreview() {
		return new ExamplePortfolioPreview();
	}

	@Override
	public List<? extends PortfolioService.PortfolioPart> getPortfolioParts() {
		ArrayList<PortfolioService.PortfolioPart> parts = new ArrayList<>();
		parts.add(new ExamplePortfolioPartOne());
		parts.add(new ExamplePortfolioPartTwo());
		return parts;
	}
}
