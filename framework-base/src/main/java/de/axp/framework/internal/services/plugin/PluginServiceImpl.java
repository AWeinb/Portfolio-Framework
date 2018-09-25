package de.axp.framework.internal.services.plugin;

import java.util.Set;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.services.PluginService;

class PluginServiceImpl implements PluginService {

	private final PluginRegistry pluginRegistry;

	PluginServiceImpl(PluginRegistry pluginRegistry) {
		this.pluginRegistry = pluginRegistry;
	}

	@Override
	public void disposeService() {

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
