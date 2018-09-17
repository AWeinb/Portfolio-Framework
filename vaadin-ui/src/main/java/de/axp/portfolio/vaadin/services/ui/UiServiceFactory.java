package de.axp.portfolio.vaadin.services.ui;

import de.axp.framework.api.PortfolioFramework;
import de.axp.portfolio.vaadin.services.ui.api.UiService;

public final class UiServiceFactory {

	private UiServiceFactory() {
	}

	public static UiService createUiService(PortfolioFramework framework) {
		return new UiServiceImpl(framework);
	}
}
