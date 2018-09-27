package de.axp.framework.internal.services.ui;

import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.UiService;

public final class UiServiceFactory {

	private UiServiceFactory() {
	}

	public static UiService createUiService(ServiceService serviceService) {
		return new UiServiceImpl(serviceService);
	}
}
