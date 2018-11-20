package de.axp.framework.api;

public interface ServiceManager extends FrameworkService {

	<T extends FrameworkService> void registerNewService(Class<T> type, T service);

	<T extends FrameworkService> T getService(Class<T> type);

	void disposeServices();
}
