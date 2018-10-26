package de.axp.portfolio.vaadin;

import de.axp.framework.api.services.TranslationService;

import java.util.HashMap;
import java.util.Map;

public class VaadinFrameworkTranslator implements TranslationService.Translator {

	private final Map<String, String> translations = new HashMap<>();

	VaadinFrameworkTranslator() {
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
