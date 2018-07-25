package de.axp.portfolio.framework.api.interfaces;

import de.axp.portfolio.framework.api.FrameworkExternalHandler;
import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.event.Event;
import de.axp.portfolio.framework.internal.service.event.EventPromiseBuilder;

public interface FrameworkEventInterface {

	void addListener(EventListener listener);

	void addListener(String context, EventListener listener);

	void fireEvent(String eventID, Object content, EventPromise promise);

	void fireEvent(String context, String eventID, Object content, EventPromise promise);

	@FunctionalInterface
	interface EventListener extends FrameworkExternalHandler {

		void handle(Event event, AnswerCallback answer);

		interface AnswerCallback extends FrameworkPromise {

			void triggerSuccess(Object result);

			void triggerFailure(Object result);
		}
	}

	interface EventPromise extends FrameworkPromise {

		static EventPromiseBuilder builder() {
			return new EventPromiseBuilder();
		}

		void on(EventPromiseResult resolution, Object result);

		enum EventPromiseResult {
			SUCCESS, REJECTION
		}
	}

}
