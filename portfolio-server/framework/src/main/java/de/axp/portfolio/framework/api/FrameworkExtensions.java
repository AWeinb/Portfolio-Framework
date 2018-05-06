package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.service.event.EventService;

public class FrameworkExtensions {

	private EventService.EventConsumer eventConsumer;

	public EventService.EventConsumer getEventConsumer() {
		return eventConsumer;
	}

	public void setEventConsumer(EventService.EventConsumer eventConsumer) {
		this.eventConsumer = eventConsumer;
	}

	public boolean isComplete() {
		return eventConsumer != null;
	}
}
