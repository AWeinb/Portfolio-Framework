package de.axp.framework.example.vaadin.internal.pages.shared;

import static de.axp.framework.api.services.PortfolioService.PortfolioDefinition;
import static de.axp.framework.api.services.PortfolioService.PortfolioPart;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.router.QueryParameters;

public class QueryParametersUtil {

	private static final String PART_ID = "part";
	private static final String PORTFOLIO_ID = "id";

	public static QueryParameters build(Map<String, List<String>> map, PortfolioDefinition portfolioDefinition,
	                                    PortfolioPart portfolioPart) {
		map.clear();
		if (portfolioDefinition != null) {
			map.put(PORTFOLIO_ID, Collections.singletonList(portfolioDefinition.getPortfolioId()));
		}
		if (portfolioPart != null) {
			map.put(PART_ID, Collections.singletonList(portfolioPart.getPartId()));
		}

		return new QueryParameters(map);
	}

	public static String extractPortfolioId(QueryParameters queryParameters) {
		List<String> strings = queryParameters.getParameters().get(PORTFOLIO_ID);
		if (strings == null) {
			return "";
		}
		return strings.get(0);
	}

	public static String extractPartId(QueryParameters queryParameters) {
		List<String> strings = queryParameters.getParameters().get(PART_ID);
		if (strings == null) {
			return "";
		}
		return strings.get(0);
	}
}
