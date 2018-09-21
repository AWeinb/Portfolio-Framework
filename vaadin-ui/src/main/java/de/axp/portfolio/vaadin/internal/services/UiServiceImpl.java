package de.axp.portfolio.vaadin.internal.services;

import java.util.Set;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.PluginService;
import de.axp.portfolio.vaadin.api.plugins.PortfolioDefinition;
import de.axp.portfolio.vaadin.api.services.UiService;

class UiServiceImpl implements UiService {

	private final PortfolioFramework framework;

	UiServiceImpl(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void disposeService() {

	}

	@Override
	public void registerPortfolioDefinition(PortfolioDefinition definition) {
		PluginService pluginService = framework.getPluginService();
		pluginService.addPlugin(PortfolioDefinition.class, definition);
	}

	@Override
	public Set<PortfolioDefinition> getPortfolioDefinitions() {
		PluginService pluginService = framework.getPluginService();
		return pluginService.getPlugins(PortfolioDefinition.class);
	}

	@Override
	public PortfolioDefinition getPortfolioDefinition(String portfolioId) {
		return getPortfolioDefinitions().stream() //
				.filter(d -> d.getPortfolioId().equals(portfolioId)) //
				.findFirst() //
				.orElse(new FallbackPortfolioDefinition());
	}
}
