package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;

public interface ServiceService extends FrameworkService {

	<T extends FrameworkService> void registerNewService(Class<T> type, T service);

	<T extends FrameworkService> T getService(Class<T> type);

	void disposeServices();
}
