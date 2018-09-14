package de.axp.framework.internal;

import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

public final class InternalFactory {

	private InternalFactory() {
	}

	public static PortfolioFrameworkImpl createFramework() {
		MainLoop mainLoop = new MainLoop();
		PluginRegistry pluginRegistry = new PluginRegistry();
		ServiceRegistry serviceRegistry = new ServiceRegistry(mainLoop, pluginRegistry);
		return new PortfolioFrameworkImpl(mainLoop, serviceRegistry);
	}
}
