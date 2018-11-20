package de.axp.framework.example;

import java.util.HashMap;
import java.util.Map;

import de.axp.framework.api.services.TranslationService;

public class ExampleTranslator implements TranslationService.Translator {

	private final Map<String, String> translations = new HashMap<>();

	public ExampleTranslator() {
		translations.put("no-portfolios-registered", "No Portfolios are registered.");
		translations.put("portfolio-not-available", "Upps! There is nothing like that!");

	}

	@Override
	public String getTranslatorId() {
		return getClass().getSimpleName();
	}

	@Override
	public String translate(String key) {
		return translations.getOrDefault(key, key + " (!)");
	}
}
