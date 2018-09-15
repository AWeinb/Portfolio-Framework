package de.axp.framework.internal.services.plugin;

import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;

class PluginServiceImpl implements PluginService {

	private final PluginRegistry pluginRegistry;

	PluginServiceImpl(PluginRegistry pluginRegistry) {
		this.pluginRegistry = pluginRegistry;
	}

	@Override
	public <T extends FrameworkPlugin> void addPlugin(Class<T> type, T plugin) {
		pluginRegistry.addPlugin(type, plugin);
	}
}
