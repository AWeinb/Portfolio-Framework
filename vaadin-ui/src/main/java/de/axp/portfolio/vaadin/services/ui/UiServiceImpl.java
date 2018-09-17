package de.axp.portfolio.vaadin.services.ui;

import java.util.Set;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.PluginService;
import de.axp.portfolio.vaadin.services.ui.api.PortfolioDefinition;
import de.axp.portfolio.vaadin.services.ui.api.UiService;

class UiServiceImpl implements UiService {

	private final PortfolioFramework framework;

	UiServiceImpl(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void disposeService() {

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
