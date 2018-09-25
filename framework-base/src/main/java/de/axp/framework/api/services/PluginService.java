package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.FrameworkService;

import java.util.Set;

public interface PluginService extends FrameworkService {

	<T extends FrameworkPlugin> void addPlugin(Class<T> type, T plugin);

	<T extends FrameworkPlugin> Set<T> getPlugins(Class<T> type);
}
