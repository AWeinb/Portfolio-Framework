package de.axp.framework.internal.services.translation;

import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.ServiceManager;
import de.axp.framework.api.services.TranslationService;

import java.util.Set;

class TranslationServiceImpl implements TranslationService {

	private final ServiceManager serviceManager;

	TranslationServiceImpl(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public void disposeService() {

	}

	@Override
	public void registerTranslator(Translator translator) {
		PluginService pluginService = serviceManager.getService(PluginService.class);
		pluginService.addPlugin(Translator.class, translator);
	}

	@Override
	public String translate(String translatorId, String key) {
		PluginService pluginService = serviceManager.getService(PluginService.class);
		Set<Translator> translators = pluginService.getPlugins(Translator.class);
		for (Translator translator : translators) {
			if (translator.getTranslatorId().equals(translatorId)) {
				return translator.translate(key);
			}
		}
		return key;
	}
}
