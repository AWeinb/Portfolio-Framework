package de.axp.framework.internal.services;

import de.axp.framework.api.ServiceManager;

public final class ServiceManagerFactory {

	private ServiceManagerFactory() {
	}

	public static ServiceManager createServiceManager() {
		ServiceRegistry serviceRegistry = new ServiceRegistry();
		return new ServiceManagerImpl(serviceRegistry);
	}
}
