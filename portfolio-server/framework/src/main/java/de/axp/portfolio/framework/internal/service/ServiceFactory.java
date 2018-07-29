package de.axp.portfolio.framework.internal.service;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.service.event.TaskService;
import de.axp.portfolio.framework.internal.service.event.EventServiceFactory;
import de.axp.portfolio.framework.internal.service.session.SessionFactory;
import de.axp.portfolio.framework.internal.service.session.SessionService;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

	public static ServiceRegistry createServiceRegistry(MainLoop mainLoop) {
		Map<Class, InternalFrameworkService> internalFrameworkServices = new HashMap<>();
		internalFrameworkServices.put(TaskService.class, EventServiceFactory.createEventService(mainLoop));
		internalFrameworkServices.put(SessionService.class, SessionFactory.createSessionService());
		return new ServiceRegistryImpl(internalFrameworkServices);
	}
}
