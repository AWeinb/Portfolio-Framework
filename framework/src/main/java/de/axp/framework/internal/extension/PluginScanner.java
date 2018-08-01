package de.axp.framework.internal.extension;

import de.axp.framework.api.extension.FrameworkPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

class PluginScanner {

	List<FrameworkPlugin> getPlugins() {
		ServiceLoader<FrameworkPlugin> serviceLoader = ServiceLoader.load(FrameworkPlugin.class);
		ArrayList<FrameworkPlugin> plugins = new ArrayList<>();

		try {
			for (FrameworkPlugin plugin : serviceLoader) {
				plugins.add(plugin);
			}
		} catch (ServiceConfigurationError e) {
			throw new FrameworkPluginException(e);
		}

		return plugins;
	}
}
