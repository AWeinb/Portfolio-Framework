package de.axp.framework.internal.plugin;

import de.axp.framework.api.FrameworkPlugin;

import java.util.Collections;
import java.util.List;

public class PluginRegistry {

	private final List<FrameworkPlugin> plugins;

	public PluginRegistry() {
		PluginScanner pluginScanner = new PluginScanner();
		plugins = Collections.unmodifiableList(pluginScanner.getPlugins());
	}

	public List<FrameworkPlugin> getPlugins() {
		return plugins;
	}
}
