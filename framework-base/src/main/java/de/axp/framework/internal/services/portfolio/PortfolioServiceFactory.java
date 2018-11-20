package de.axp.framework.internal.services.portfolio;

import de.axp.framework.api.ServiceManager;
import de.axp.framework.api.services.PortfolioService;

public final class PortfolioServiceFactory {

	private PortfolioServiceFactory() {
	}

	public static PortfolioService createPortfolioService(ServiceManager serviceManager) {
		return new PortfolioServiceImpl(serviceManager);
	}
}
