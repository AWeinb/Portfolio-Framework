package de.axp.portfolio.framework.internal.service;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class ServiceFactory {

	public static ServiceRegistry createServiceRegistry(MainLoop mainLoop) {
		return new ServiceRegistryImpl(mainLoop);
	}
}
