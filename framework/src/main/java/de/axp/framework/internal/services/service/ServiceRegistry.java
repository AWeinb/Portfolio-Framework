package de.axp.framework.internal.services.service;

import java.util.HashMap;
import java.util.Map;

import de.axp.framework.api.FrameworkService;

class ServiceRegistry {

	private final Map<Class<? extends FrameworkService>, FrameworkService> serviceMap = new HashMap<>();

	<T extends FrameworkService> T getService(Class<T> serviceClass) {
		return (T) serviceMap.get(serviceClass);
	}

	<T extends FrameworkService> void putService(Class<T> serviceClass, T service) {
		serviceMap.put(serviceClass, service);
	}

	void disposeAll() {
		serviceMap.values().forEach(FrameworkService::disposeService);
	}
}
