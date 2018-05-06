package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class EventServiceFactory {

	public static EventService createCommandService(MainLoop mainLoop, EventService.EventHandler eventHandler) {
		EventServiceImpl eventService = new EventServiceImpl(eventHandler);
		mainLoop.addPlugin(eventService);
		return eventService;
	}
}
