package de.axp.framework.internal.services.data;

import de.axp.framework.api.services.DataService;
import de.axp.framework.internal.services.service.ServiceRegistry;

class DataServiceImpl implements DataService {

	private final ServiceRegistry serviceRegistry;

	DataServiceImpl(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}
