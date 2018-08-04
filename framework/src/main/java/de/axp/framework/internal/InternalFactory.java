package de.axp.framework.internal;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopFactory;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry;

public class InternalFactory {

	public static PortfolioFramework.BasePortfolioFramework createFramework() {
		MainLoop mainLoop = MainLoopFactory.createMainLoop();
		BaseServiceRegistry baseServiceRegistry = new BaseServiceRegistry(mainLoop);
		PluginRegistry pluginRegistry = new PluginRegistry();
		return new BasePortfolioFrameworkImpl(mainLoop, baseServiceRegistry, pluginRegistry);
	}
}
