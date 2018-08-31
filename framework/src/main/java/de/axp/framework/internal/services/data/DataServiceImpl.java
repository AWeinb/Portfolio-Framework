package de.axp.framework.internal.services.data;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.extensions.DataHandler;
import de.axp.framework.api.services.DataService;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry;

class DataServiceImpl implements DataService {

	private final BaseServiceRegistry serviceRegistry;
	private final PluginRegistry pluginRegistry;
	private final PortfolioFramework.FrameworkSession session;

	DataServiceImpl(BaseServiceRegistry serviceRegistry, PluginRegistry pluginRegistry,
	                PortfolioFramework.FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.pluginRegistry = pluginRegistry;
		this.session = session;
	}

	@Override
	public void addDataHandler(DataHandler dataHandler) {
		BaseDataService internalDataService = serviceRegistry.getBaseService(BaseDataService.class);
		internalDataService.register(session.toString(), dataHandler.provideIdentifier(), dataHandler);

	}
}
