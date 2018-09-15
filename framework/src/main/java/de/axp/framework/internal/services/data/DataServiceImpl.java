package de.axp.framework.internal.services.data;

import de.axp.framework.api.services.DataService;
import de.axp.framework.internal.services.plugin.PluginRegistry;
import de.axp.framework.internal.services.service.ServiceRegistry;

class DataServiceImpl implements DataService {

	private final ServiceRegistry serviceRegistry;
	private final PluginRegistry pluginRegistry;

	DataServiceImpl(ServiceRegistry serviceRegistry, PluginRegistry pluginRegistry) {
		this.serviceRegistry = serviceRegistry;
		this.pluginRegistry = pluginRegistry;
	}
}
