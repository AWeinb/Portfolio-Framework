package de.axp.framework.internal.infrastructure.plugin;

import java.util.HashSet;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;

final class PluginScanner {

	private PluginScanner() {
	}

	static <P extends FrameworkPlugin> Set<P> getPlugins(Class<P> service) {
		ServiceLoader<P> serviceLoader = ServiceLoader.load(service);
		Set<P> plugins = new HashSet<>();

		try {
			for (P plugin : serviceLoader) {
				plugins.add(plugin);
			}
		} catch (ServiceConfigurationError e) {
			throw new FrameworkPluginException(e);
		}

		return plugins;
	}
}
