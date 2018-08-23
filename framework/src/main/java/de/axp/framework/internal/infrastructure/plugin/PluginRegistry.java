package de.axp.framework.internal.infrastructure.plugin;

import java.util.*;

public class PluginRegistry {

	private final Map<Class<? extends FrameworkPlugin>, Set<? extends FrameworkPlugin>> plugins = new HashMap<>();

	public PluginRegistry() {
	}

	private void addPluginsOfType(Class<? extends FrameworkPlugin> aClass) {
		plugins.put(aClass, PluginScanner.getPlugins(aClass));
	}

	public Collection<? extends FrameworkPlugin> getPlugins() {
		Set<FrameworkPlugin> allPlugins = new HashSet<>();
		for (Set<? extends FrameworkPlugin> value : plugins.values()) {
			allPlugins.addAll(value);
		}
		return allPlugins;
	}

	public <P extends FrameworkPlugin> Set<P> getPluginsOfType(Class<P> type) {
		return (Set<P>) plugins.get(type);
	}
}
