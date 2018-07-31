package de.axp.portfolio.framework.internal.service;

public interface ServiceRegistry {

	InternalFrameworkService get(Class serviceClass);

	void disposeAll();
}
