package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.FrameworkExternalHandler;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface EventService extends InternalFrameworkService {

	void dispatchEvent(Event event) throws InterruptedException;

	interface EventConsumer extends FrameworkExternalHandler {

		void consume(Event event);
	}
}
