package de.axp.framework.internal.services.service;

import de.axp.framework.api.services.ServiceService;
import de.axp.framework.internal.services.ServiceRegistry;

public final class ServiceServiceFactory {

	private ServiceServiceFactory() {
	}

	public static ServiceService createServiceService(ServiceRegistry serviceRegistry) {
		return new ServiceServiceImpl(serviceRegistry);
	}
}
