package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.FrameworkService;

public interface TranslationService extends FrameworkService {

	void registerTranslator(Translator translator);

	String translate(String translatorId, String key);

	interface Translator extends FrameworkPlugin {

		String getTranslatorId();

		String translate(String key);
	}
}
