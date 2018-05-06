package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.FrameworkException;
import de.axp.portfolio.framework.api.FrameworkExtensions;
import de.axp.portfolio.framework.api.SessionFramework;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopFactory;
import de.axp.portfolio.framework.internal.service.ServiceFactory;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

class FrameworkImpl implements Framework {

	private final Map<String, Object> attributes = new HashMap<>();

	private MainLoop mainLoop;
	private ServiceRegistry serviceRegistry;

	FrameworkImpl(FrameworkExtensions frameworkExtensions) {
		if (!frameworkExtensions.isComplete()) {
			throw new IncompleteFrameworkExtensionsException();
		}

		mainLoop = MainLoopFactory.createMainLoop();
		serviceRegistry = ServiceFactory.createServiceRegistry(frameworkExtensions, mainLoop);
	}

	@Override
	public void dispose() {
		serviceRegistry.disposeAll();
		mainLoop.dispose();
	}

	@Override
	public SessionFramework adaptForSession(String sessionID) {
		return new SessionFrameworkImpl(this, sessionID);
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

	public class IncompleteFrameworkExtensionsException extends FrameworkException {

	}
}
