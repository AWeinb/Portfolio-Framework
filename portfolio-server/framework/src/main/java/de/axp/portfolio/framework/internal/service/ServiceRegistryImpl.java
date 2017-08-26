package de.axp.portfolio.framework.internal.service;

import de.axp.portfolio.framework.api.FrameworkExtensions;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.service.command.CommandFactory;
import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.session.SessionFactory;
import de.axp.portfolio.framework.internal.service.session.SessionService;
import de.axp.portfolio.framework.internal.service.ui.UiFactory;
import de.axp.portfolio.framework.internal.service.ui.UiService;

import java.util.HashMap;
import java.util.Map;

class ServiceRegistryImpl implements ServiceRegistry {

	private Map<Class, FrameworkService> serviceMap = new HashMap<>();

	ServiceRegistryImpl(FrameworkExtensions frameworkExtensions, MainLoop mainLoop) {
		serviceMap.put(CommandService.class, getCommandService(frameworkExtensions, mainLoop));
		serviceMap.put(SessionService.class, getSessionService());
		serviceMap.put(UiService.class, getUiService(frameworkExtensions));
	}

	@Override
	public FrameworkService get(Class serviceClass) {
		return serviceMap.get(serviceClass);
	}

	@Override
	public void disposeAll() {
		for (FrameworkService frameworkService : serviceMap.values()) {
			frameworkService.dispose();
		}
	}

	private CommandService getCommandService(FrameworkExtensions frameworkExtensions, MainLoop mainLoop) {
		return CommandFactory.createCommandService(mainLoop, frameworkExtensions.getCommandHandler());
	}

	private SessionService getSessionService() {
		return SessionFactory.createSessionService();
	}

	private UiService getUiService(FrameworkExtensions frameworkExtensions) {
		return UiFactory.createUiService(frameworkExtensions.getUiChangeHandler());
	}
}
