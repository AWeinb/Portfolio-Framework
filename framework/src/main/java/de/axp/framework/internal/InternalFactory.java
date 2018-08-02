package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopFactory;
import de.axp.framework.internal.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

public class InternalFactory {

	public static PortfolioFramework.BasePortfolioFramework createFramework() {
		MainLoop mainLoop = MainLoopFactory.createMainLoop();
		ServiceRegistry serviceRegistry = new ServiceRegistry(mainLoop);
		PluginRegistry pluginRegistry = new PluginRegistry();
		return new BasePortfolioFrameworkImpl(mainLoop, serviceRegistry, pluginRegistry);
	}
}
