package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.FrameworkService;

public interface PluginService extends FrameworkService {

	<T extends FrameworkPlugin> void addPlugin(Class<T> type, T plugin);
}
