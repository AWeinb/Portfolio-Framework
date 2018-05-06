package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class EventServiceFactory {

	public static EventService createEventService(MainLoop mainLoop) {
		EventServiceImpl eventService = new EventServiceImpl();
		mainLoop.addPlugin(eventService);
		return eventService;
	}
}
