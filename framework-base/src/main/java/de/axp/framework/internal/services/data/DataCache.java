package de.axp.framework.internal.services.data;

import java.util.HashMap;

class DataCache {

	private final HashMap<String, Object> cache = new HashMap<>();

	boolean contains(String id) {
		return cache.containsKey(id);
	}

	Object get(String id) {
		return cache.get(id);
	}

	void set(String id, Object data) {
		cache.put(id, data);
	}
}
