package de.axp.framework.internal.service;

public interface ServiceRegistry {

	InternalFrameworkService get(Class serviceClass);

	void disposeAll();
}
