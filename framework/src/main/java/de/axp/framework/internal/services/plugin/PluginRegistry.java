package de.axp.framework.internal.services.plugin;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.plugins.DataHandler;
import de.axp.framework.api.plugins.TaskHandler;

import java.util.*;

class PluginRegistry {

	private final Map<Class<?>, Set<FrameworkPlugin>> plugins = new HashMap<>();

	PluginRegistry() {
		initPluginsOfType(DataHandler.class);
		initPluginsOfType(TaskHandler.class);
	}

	private void initPluginsOfType(Class<? extends FrameworkPlugin> type) {
		PluginScanner.getPlugins(type).forEach(p -> this.addPlugin(type, p));
	}

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
