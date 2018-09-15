package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;

public interface DataService extends FrameworkService {

	String get(String id);

	void set(String id, String data);

}
