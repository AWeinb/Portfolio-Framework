package de.axp.portfolio.framework.internal.service;

public interface ServiceRegistry {

	FrameworkService get(Class serviceClass);

	void disposeAll();
}
