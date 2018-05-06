package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class EventServiceFactory {

	public static EventService createCommandService(MainLoop mainLoop, EventService.EventConsumer eventConsumer) {
		EventServiceImpl eventService = new EventServiceImpl(eventConsumer);
		mainLoop.addPlugin(eventService);
		return eventService;
	}
}
