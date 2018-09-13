package de.axp.framework.internal.services.data;

import de.axp.framework.api.extensions.DataHandler;
import de.axp.framework.api.services.DataService;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

class DataServiceImpl implements DataService {

	private final ServiceRegistry serviceRegistry;
	private final PluginRegistry pluginRegistry;

	DataServiceImpl(ServiceRegistry serviceRegistry, PluginRegistry pluginRegistry) {
		this.serviceRegistry = serviceRegistry;
		this.pluginRegistry = pluginRegistry;
	}

	@Override
	public void addDataHandler(DataHandler dataHandler) {
		// BaseDataService internalDataService = serviceRegistry.getBaseService(BaseDataService.class);
		// internalDataService.register(session.toString(), dataHandler.provideIdentifier(), dataHandler);
	}
}
