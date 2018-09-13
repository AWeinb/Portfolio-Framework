package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.internal.infrastructure.plugin.FrameworkPlugin;

public interface PluginService extends FrameworkService {

	<T extends FrameworkPlugin> void addPlugin(Class<T> type, T plugin);
}
