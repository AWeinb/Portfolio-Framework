package de.axp.framework.internal.services.plugin;

public final class PluginServiceFactory {

	private PluginServiceFactory() {
	}

	public static PluginServiceImpl createPluginService() {
		PluginRegistry pluginRegistry = new PluginRegistry();
		return new PluginServiceImpl(pluginRegistry);
	}
}
