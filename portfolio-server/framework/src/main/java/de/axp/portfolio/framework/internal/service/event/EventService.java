package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkExternalHandler;
import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

import java.util.Optional;

public interface EventService extends InternalFrameworkService {

	void addEventConsumer(String sessionId, String context, EventConsumer eventConsumer);

	void dispatchEvent(Event event) throws InterruptedException;

	interface Event {

		static Event build(String sessionId, String context, String packageId, Object content,
		                   FrameworkPromise promise) {
			return new EventImpl(sessionId, context, packageId, content, promise);
		}

		static Event buildOneWay(String sessionId, String context, String packageId, Object content) {
			return new EventImpl(sessionId, context, packageId, content, null);
		}

		String getSessionId();

		String getContext();

		String getPackageId();

		Object getData();

		Optional<FrameworkPromise> getPromise();
	}

	interface EventConsumer extends FrameworkExternalHandler {

		void consume(Event event);
	}
}
