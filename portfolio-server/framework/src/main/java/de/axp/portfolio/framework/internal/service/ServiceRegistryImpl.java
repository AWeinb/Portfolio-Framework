package de.axp.portfolio.framework.internal.service;

import de.axp.portfolio.framework.api.interaction.FrameworkExtensions;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.service.command.CommandFactory;
import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.session.SessionFactory;
import de.axp.portfolio.framework.internal.service.session.SessionService;

import java.util.HashMap;
import java.util.Map;

class ServiceRegistryImpl implements ServiceRegistry {

	private Map<Class, InternalFrameworkService> internalFrameworkServices = new HashMap<>();

	ServiceRegistryImpl(FrameworkExtensions frameworkExtensions, MainLoop mainLoop) {
		internalFrameworkServices.put(CommandService.class, getCommandService(frameworkExtensions, mainLoop));
		internalFrameworkServices.put(SessionService.class, getSessionService());
	}

	@Override
	public InternalFrameworkService get(Class serviceClass) {
		return internalFrameworkServices.get(serviceClass);
	}

	@Override
	public void disposeAll() {
		for (InternalFrameworkService internalFrameworkService : internalFrameworkServices.values()) {
			internalFrameworkService.dispose();
		}
	}

	private CommandService getCommandService(FrameworkExtensions frameworkExtensions, MainLoop mainLoop) {
		return CommandFactory.createCommandService(mainLoop, frameworkExtensions.getCommandHandler());
	}

	private SessionService getSessionService() {
		return SessionFactory.createSessionService();
	}
}
