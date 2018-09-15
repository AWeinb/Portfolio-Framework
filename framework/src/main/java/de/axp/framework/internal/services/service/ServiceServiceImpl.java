package de.axp.framework.internal.services.service;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.services.ServiceService;

class ServiceServiceImpl implements ServiceService {

	private final ServiceRegistry serviceRegistry;

	ServiceServiceImpl(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public <T extends FrameworkService> void registerNewService(Class<T> type, T service) {
		serviceRegistry.putService(type, service);
	}

	@Override
	public <T extends FrameworkService> T getService(Class<T> type) {
		return serviceRegistry.getService(type);
	}

	@Override
	public void disposeServices() {
		serviceRegistry.disposeAll();
	}
}
