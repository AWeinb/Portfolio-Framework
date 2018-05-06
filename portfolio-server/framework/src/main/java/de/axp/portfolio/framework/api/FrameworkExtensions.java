package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.service.event.EventService;

public class FrameworkExtensions {

	private EventService.EventHandler eventHandler;

	public EventService.EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventService.EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	public boolean isComplete() {
		return eventHandler != null;
	}
}
