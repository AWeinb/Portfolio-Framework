package de.axp.framework.internal.services.service;

import de.axp.framework.api.FrameworkService;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {

	private final Map<Class<? extends FrameworkService>, FrameworkService> serviceMap = new HashMap<>();

	public <T extends FrameworkService> T getService(Class<T> serviceClass) {
		return (T) serviceMap.get(serviceClass);
	}

	public <T extends FrameworkService> void putService(Class<T> serviceClass, T service) {
		serviceMap.put(serviceClass, service);
	}

	public void disposeAll() {

	}
}
