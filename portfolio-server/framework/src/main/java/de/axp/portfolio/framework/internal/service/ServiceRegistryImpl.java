package de.axp.portfolio.framework.internal.service;

import java.util.Map;

class ServiceRegistryImpl implements ServiceRegistry {

	private final Map<Class, InternalFrameworkService> serviceMap;

	ServiceRegistryImpl(Map<Class, InternalFrameworkService> serviceMap) {
		this.serviceMap = serviceMap;
	}

	@Override
	public InternalFrameworkService get(Class serviceClass) {
		return serviceMap.get(serviceClass);
	}

	@Override
	public void disposeAll() {
		for (InternalFrameworkService internalFrameworkService : serviceMap.values()) {
			internalFrameworkService.dispose();
		}
	}
}
