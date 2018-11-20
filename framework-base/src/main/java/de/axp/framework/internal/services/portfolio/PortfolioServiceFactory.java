package de.axp.framework.internal.services.portfolio;

import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.PortfolioService;

public final class PortfolioServiceFactory {

	private PortfolioServiceFactory() {
	}

	public static PortfolioService createPortfolioService(ServiceService serviceService) {
		return new PortfolioServiceImpl(serviceService);
	}
}
