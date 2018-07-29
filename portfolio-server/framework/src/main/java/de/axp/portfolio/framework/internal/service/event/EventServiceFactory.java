package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class EventServiceFactory {

	public static TaskService createEventService(MainLoop mainLoop) {
		TaskServiceImpl eventService = new TaskServiceImpl();
		mainLoop.addPlugin(eventService);
		return eventService;
	}
}
