package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.BaseFramework;
import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopFactory;
import de.axp.portfolio.framework.internal.service.ServiceFactory;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

class BaseFrameworkImpl implements BaseFramework {

	private final Map<String, Object> attributes = new HashMap<>();

	private MainLoop mainLoop;
	private ServiceRegistry serviceRegistry;

	BaseFrameworkImpl() {
		mainLoop = MainLoopFactory.createMainLoop();
		serviceRegistry = ServiceFactory.createServiceRegistry(mainLoop);
	}

	@Override
	public void dispose() {
		serviceRegistry.disposeAll();
		mainLoop.dispose();
	}

	@Override
	public AuthenticatedFramework adaptForSession(String sessionID) {
		return new AuthenticatedFrameworkImpl(this, sessionID);
	}

	@Override
	public void addAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
}
