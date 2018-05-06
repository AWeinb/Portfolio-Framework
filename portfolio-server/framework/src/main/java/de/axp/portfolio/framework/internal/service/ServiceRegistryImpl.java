package de.axp.portfolio.framework.internal.service;

import de.axp.portfolio.framework.api.FrameworkExtensions;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.service.event.EventService;
import de.axp.portfolio.framework.internal.service.event.EventServiceFactory;
import de.axp.portfolio.framework.internal.service.session.SessionFactory;
import de.axp.portfolio.framework.internal.service.session.SessionService;

import java.util.HashMap;
import java.util.Map;

class ServiceRegistryImpl implements ServiceRegistry {

	private Map<Class, InternalFrameworkService> internalFrameworkServices = new HashMap<>();

	ServiceRegistryImpl(FrameworkExtensions frameworkExtensions, MainLoop mainLoop) {
		internalFrameworkServices.put(EventService.class, getEventService(frameworkExtensions, mainLoop));
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

	private EventService getEventService(FrameworkExtensions frameworkExtensions, MainLoop mainLoop) {
		return EventServiceFactory.createCommandService(mainLoop, frameworkExtensions.getEventHandler());
	}

	private SessionService getSessionService() {
		return SessionFactory.createSessionService();
	}
}
