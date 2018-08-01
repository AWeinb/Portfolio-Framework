package de.axp.framework.internal.extension;

import de.axp.framework.api.extension.FrameworkPlugin;

import java.util.List;

public class ExtensionFactory {

	private static PluginScanner pluginScanner = new PluginScanner();

	public static List<FrameworkPlugin> getPlugins() {
		return pluginScanner.getPlugins();
	}
}
