package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.api.FrameworkExternalHandler;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

import java.util.Optional;

public interface EventService extends InternalFrameworkService {

	void dispatchEvent(Event event) throws InterruptedException;

	interface Event {

		static Event build(String sessionID, String packageID, Object content, FrameworkPromise promise) {
			return new EventImpl(sessionID, packageID, content, promise);
		}

		static Event buildOneWay(String sessionID, String packageID, Object content) {
			return new EventImpl(sessionID, packageID, content, null);
		}

		String getSessionID();

		String getPackageID();

		Object getData();

		Optional<FrameworkPromise> getPromise();
	}

	interface EventConsumer extends FrameworkExternalHandler {

		void consume(Event event);
	}
}
