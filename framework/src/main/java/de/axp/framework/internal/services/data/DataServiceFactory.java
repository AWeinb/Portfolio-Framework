package de.axp.framework.internal.services.data;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.ServiceService;

public final class DataServiceFactory {

	private DataServiceFactory() {
	}

	public static DataService createDataService(ServiceService serviceService) {
		DataCache dataCache = new DataCache();
		return new DataServiceImpl(dataCache);
	}
}
