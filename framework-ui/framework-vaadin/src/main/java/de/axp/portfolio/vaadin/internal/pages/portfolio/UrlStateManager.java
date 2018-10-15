package de.axp.portfolio.vaadin.internal.pages.portfolio;

import java.util.Optional;

import de.axp.framework.api.services.UiService;

class UrlStateManager {

	private final UiService uiService;
	private final UrlState urlState = new UrlState();

	UrlStateManager(UiService uiService) {
		this.uiService = uiService;
	}

	UrlState getUrlState() {
		return urlState;
	}

	void updateRootSegment(String rootSegment) {
		urlState.setRootSegment(rootSegment);
	}

	void updateParameterSegment(String parameter) {
		String[] split = parameter.split("/");

		String pageSegment = split.length > 0 ? split[0] : "";
		urlState.setPageSegment(pageSegment);

		Optional<UiService.PortfolioDefinition> portfolioDefinition = uiService.getPortfolioDefinition(pageSegment);
		int partCount = portfolioDefinition.orElse(new FallbackPortfolioDefinition()).getPortfolioParts().size() - 1;
		urlState.setFirstPartIndex(0);
		urlState.setLastPartIndex(partCount);

		try {
			int partIndex = Integer.parseInt(split.length > 1 ? split[1] : "0");
			partIndex = Math.max(urlState.getFirstPartIndex(), partIndex);
			partIndex = Math.min(partIndex, urlState.getLastPartIndex());
			urlState.setPartIndex(partIndex);
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
	}
}
