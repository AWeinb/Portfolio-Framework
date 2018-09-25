package de.axp.framework.internal.services.plugin;

import de.axp.framework.api.FrameworkPlugin;

import java.util.*;

class PluginRegistry {

	private final Map<Class<?>, Set<FrameworkPlugin>> plugins = new HashMap<>();

	void addPlugin(Class<? extends FrameworkPlugin> type, FrameworkPlugin frameworkPlugin) {
		if (!plugins.containsKey(type)) {
			plugins.put(type, new HashSet<>());
		}
		plugins.get(type).add(frameworkPlugin);
	}

	<T extends FrameworkPlugin> Set<T> getPlugins(Class<T> type) {
		return (Set<T>) plugins.getOrDefault(type, Collections.emptySet());
	}
}
