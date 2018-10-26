package de.axp.framework.internal.services.translation;

import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TranslationService;

public final class TranslationServiceFactory {

	private TranslationServiceFactory() {
	}

	public static TranslationService createTranslationService(ServiceService serviceService) {
		return new TranslationServiceImpl(serviceService);
	}
}
