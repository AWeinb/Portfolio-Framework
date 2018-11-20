package de.axp.framework.internal.services.translation;

import de.axp.framework.api.ServiceManager;
import de.axp.framework.api.services.TranslationService;

public final class TranslationServiceFactory {

	private TranslationServiceFactory() {
	}

	public static TranslationService createTranslationService(ServiceManager serviceManager) {
		return new TranslationServiceImpl(serviceManager);
	}
}
