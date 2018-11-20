package de.axp.framework.internal.services.data;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.ServiceManager;

public final class DataServiceFactory {

	private DataServiceFactory() {
	}

	public static DataService createDataService(ServiceManager serviceManager) {
		DataCache dataCache = new DataCache();
		return new DataServiceImpl(serviceManager, dataCache);
	}
}
