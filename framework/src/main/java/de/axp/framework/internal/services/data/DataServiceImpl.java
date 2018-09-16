package de.axp.framework.internal.services.data;

import de.axp.framework.api.services.DataService;

class DataServiceImpl implements DataService {

	private final DataCache dataCache;

	DataServiceImpl(DataCache dataCache) {
		this.dataCache = dataCache;
	}

	@Override
	public void disposeService() {

	}

	@Override
	public String get(String id) {
		return dataCache.get(id);
	}

	@Override
	public void set(String id, String data) {
		dataCache.set(id, data);
	}
}
