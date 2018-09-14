package de.axp.framework.internal.services.data;

import de.axp.framework.api.services.DataService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

public final class DataServiceFactory {

	private DataServiceFactory() {
	}

	public static DataService createDataService(MainLoop mainLoop, ServiceRegistry serviceRegistry,
			PluginRegistry pluginRegistry) {
		return null;
	}
}
