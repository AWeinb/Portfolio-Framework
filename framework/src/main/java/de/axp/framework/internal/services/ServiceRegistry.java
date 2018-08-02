package de.axp.framework.internal.services;

import java.util.Map;

public class ServiceRegistry {

	private final Map<Class, InternalFrameworkService> serviceMap;

	public ServiceRegistry(Map<Class, InternalFrameworkService> serviceMap) {
		this.serviceMap = serviceMap;
	}

	public InternalFrameworkService get(Class serviceClass) {
		return serviceMap.get(serviceClass);
	}
}
