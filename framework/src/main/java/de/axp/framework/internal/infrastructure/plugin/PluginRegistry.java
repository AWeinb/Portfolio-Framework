package de.axp.framework.internal.infrastructure.plugin;

import de.axp.framework.api.interfaces.data.DataHandler;

import java.util.*;

public class PluginRegistry {

	private final Map<Class, Set<? extends FrameworkPlugin>> plugins = new HashMap<>();

	public PluginRegistry() {
		PluginScanner pluginScanner = new PluginScanner();

		plugins.put(DataHandler.class, pluginScanner.getPlugins(DataHandler.class));
	}

	public Collection<? extends FrameworkPlugin> getPlugins() {
		Set<FrameworkPlugin> allPlugins = new HashSet<>();
		for (Set<? extends FrameworkPlugin> value : plugins.values()) {
			allPlugins.addAll(value);
		}
		return allPlugins;
	}

	public Set<? extends FrameworkPlugin> getPluginsOfType(Class<? extends FrameworkPlugin> type) {
		return plugins.get(type);
	}
}
