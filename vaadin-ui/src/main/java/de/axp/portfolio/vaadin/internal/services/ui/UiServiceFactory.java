package de.axp.portfolio.vaadin.internal.services.ui;

import de.axp.framework.api.PortfolioFramework;
import de.axp.portfolio.vaadin.api.services.UiService;

public final class UiServiceFactory {

	private UiServiceFactory() {
	}

	public static UiService createUiService(PortfolioFramework framework) {
		return new UiServiceImpl(framework);
	}
}
