package de.axp.framework.internal.services.plugin;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.services.PluginService;

import java.util.Set;

class PluginServiceImpl implements PluginService {

	private final PluginRegistry pluginRegistry;

	PluginServiceImpl(PluginRegistry pluginRegistry) {
		this.pluginRegistry = pluginRegistry;
	}

	@Override
	public <T extends FrameworkPlugin> void addPlugin(Class<T> type, T plugin) {
		pluginRegistry.addPlugin(type, plugin);
	}

	@Override
	public <T extends FrameworkPlugin> Set<T> getPlugins(Class<T> type) {
		return pluginRegistry.getPlugins(type);
	}
}
