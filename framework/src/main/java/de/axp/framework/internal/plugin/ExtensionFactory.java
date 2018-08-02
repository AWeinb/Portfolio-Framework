package de.axp.framework.internal.plugin;

import de.axp.framework.api.FrameworkPlugin;

import java.util.List;

public class ExtensionFactory {

	private static PluginScanner pluginScanner = new PluginScanner();

	public static List<FrameworkPlugin> getPlugins() {
		return pluginScanner.getPlugins();
	}
}
