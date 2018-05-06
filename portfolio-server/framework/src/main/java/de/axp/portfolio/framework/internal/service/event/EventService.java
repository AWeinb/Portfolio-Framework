package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkExternalHandler;
import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

import java.util.Optional;

public interface EventService extends InternalFrameworkService {

	void addEventConsumer(String sessionID, String context, EventConsumer eventConsumer);

	void dispatchEvent(Event event) throws InterruptedException;

	interface Event {

		static Event build(String sessionID, String context, String packageID, Object content,
		                   FrameworkPromise promise) {
			return new EventImpl(sessionID, context, packageID, content, promise);
		}

		static Event buildOneWay(String sessionID, String context, String packageID, Object content) {
			return new EventImpl(sessionID, context, packageID, content, null);
		}

		String getSessionID();

		String getContext();

		String getPackageID();

		Object getData();

		Optional<FrameworkPromise> getPromise();
	}

	interface EventConsumer extends FrameworkExternalHandler {

		void consume(Event event);
	}
}
