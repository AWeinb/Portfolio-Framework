package de.axp.framework.internal.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.ServiceManager;

class ServiceManagerImpl implements ServiceManager {

	private final ServiceRegistry serviceRegistry;

	ServiceManagerImpl(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public void disposeService() {

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
