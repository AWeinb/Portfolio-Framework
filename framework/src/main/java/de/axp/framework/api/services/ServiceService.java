package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;

public interface ServiceService extends FrameworkService {

	<T extends FrameworkService> void registerNewService(Class<T> a, T v);
}
