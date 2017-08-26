package de.axp.portfolio.framework.internal.service;

import de.axp.portfolio.framework.api.FrameworkExtensions;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class ServiceFactory {

	public static ServiceRegistry createServiceRegistry(FrameworkExtensions frameworkExtensions, MainLoop mainLoop) {
		return new ServiceRegistryImpl(frameworkExtensions, mainLoop);
	}
}
