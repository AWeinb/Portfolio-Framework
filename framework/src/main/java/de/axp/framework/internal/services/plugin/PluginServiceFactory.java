package de.axp.framework.internal.services.plugin;

import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;

public class PluginServiceFactory {

	public static PluginServiceImpl createPluginService(PluginRegistry pluginRegistry) {
		return new PluginServiceImpl(pluginRegistry);
	}
}
