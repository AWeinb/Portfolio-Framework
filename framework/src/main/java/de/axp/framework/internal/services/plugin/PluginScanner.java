package de.axp.framework.internal.services.plugin;

import de.axp.framework.api.FrameworkPlugin;

import java.util.HashSet;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;

final class PluginScanner {

	private PluginScanner() {
	}

	static Set<FrameworkPlugin> getPlugins(Class<? extends FrameworkPlugin> service) {
		ServiceLoader<? extends FrameworkPlugin> serviceLoader = ServiceLoader.load(service);
		Set<FrameworkPlugin> plugins = new HashSet<>();

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
