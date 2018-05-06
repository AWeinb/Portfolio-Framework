package de.axp.portfolio.framework.api.interfaces;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.event.EventService;

public interface FrameworkEventInterface {

	void addEventConsumer(EventService.EventConsumer eventConsumer);

	void addEventConsumerForContext(String context, EventService.EventConsumer eventConsumer);

	void dispatchEvent(String eventID, Object content, FrameworkPromise promise);

	void dispatchEventInContext(String context, String eventID, Object content, FrameworkPromise promise);
}
