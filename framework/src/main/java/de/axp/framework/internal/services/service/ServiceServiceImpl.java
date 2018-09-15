package de.axp.framework.internal.services.service;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.services.ServiceService;

class ServiceServiceImpl implements ServiceService {

	private final ServiceRegistry serviceRegistry;

	ServiceServiceImpl(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public <T extends FrameworkService> void registerNewService(Class<T> serviceType, T service) {
		serviceRegistry.putService(serviceType, service);
	}
}
