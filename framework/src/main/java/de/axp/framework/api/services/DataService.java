package de.axp.framework.api.services;

public interface DataService {

	void load(String id);

	void save(String id);

	void invalidate(String id);
}
