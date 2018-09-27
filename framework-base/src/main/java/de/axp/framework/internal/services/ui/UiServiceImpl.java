package de.axp.framework.internal.services.ui;

import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.UiService;

import java.util.Optional;
import java.util.Set;

class UiServiceImpl implements UiService {

	private final ServiceService serviceService;

	UiServiceImpl(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@Override
	public void disposeService() {

	}

	@Override
	public void registerPortfolioDefinition(PortfolioDefinition definition) {
		PluginService pluginService = serviceService.getService(PluginService.class);
		pluginService.addPlugin(PortfolioDefinition.class, definition);
	}

	@Override
	public Set<PortfolioDefinition> getPortfolioDefinitions() {
		PluginService pluginService = serviceService.getService(PluginService.class);
		return pluginService.getPlugins(PortfolioDefinition.class);
	}

	@Override
	public Optional<PortfolioDefinition> getPortfolioDefinition(String portfolioId) {
		return getPortfolioDefinitions().stream() //
				.filter(d -> d.getPortfolioId().equals(portfolioId)) //
				.findFirst();
	}
}
