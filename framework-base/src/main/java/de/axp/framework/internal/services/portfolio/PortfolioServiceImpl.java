package de.axp.framework.internal.services.portfolio;

import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.ServiceManager;
import de.axp.framework.api.services.PortfolioService;

import java.util.Optional;
import java.util.Set;

class PortfolioServiceImpl implements PortfolioService {

	private final ServiceManager serviceManager;

	PortfolioServiceImpl(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public void disposeService() {

	}

	@Override
	public void registerPortfolioDefinition(PortfolioDefinition definition) {
		PluginService pluginService = serviceManager.getService(PluginService.class);
		pluginService.addPlugin(PortfolioDefinition.class, definition);
	}

	@Override
	public Set<PortfolioDefinition> getPortfolioDefinitions() {
		PluginService pluginService = serviceManager.getService(PluginService.class);
		return pluginService.getPlugins(PortfolioDefinition.class);
	}

	@Override
	public Optional<PortfolioDefinition> getPortfolioDefinition(String portfolioId) {
		return getPortfolioDefinitions().stream() //
				.filter(d -> d.getPortfolioId().equals(portfolioId)) //
				.findFirst();
	}
}
