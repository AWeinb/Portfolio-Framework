package de.axp.framework.internal.services.translation;

import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TranslationService;

import java.util.Set;

class TranslationServiceImpl implements TranslationService {

	private final ServiceService serviceService;

	TranslationServiceImpl(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@Override
	public void disposeService() {

	}

	@Override
	public void registerTranslator(Translator translator) {
		PluginService pluginService = serviceService.getService(PluginService.class);
		pluginService.addPlugin(Translator.class, translator);
	}

	@Override
	public String translate(String translatorId, String key) {
		PluginService pluginService = serviceService.getService(PluginService.class);
		Set<Translator> translators = pluginService.getPlugins(Translator.class);
		for (Translator translator : translators) {
			if (translator.getTranslatorId().equals(translatorId)) {
				return translator.translate(key);
			}
		}
		return key;
	}
}
