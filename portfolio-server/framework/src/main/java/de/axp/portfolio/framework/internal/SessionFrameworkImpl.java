package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.api.SessionFramework;
import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.service.event.EventService;
import de.axp.portfolio.framework.internal.service.session.SessionService;

import static de.axp.portfolio.framework.internal.service.event.EventService.Event;

class SessionFrameworkImpl implements SessionFramework, FrameworkEventInterface, FrameworkSessionInterface {

	private final FrameworkImpl framework;
	private final String sessionID;

	SessionFrameworkImpl(FrameworkImpl framework, String sessionID) {
		this.framework = framework;
		this.sessionID = sessionID;
	}

	@Override
	public void dispose() {
		framework.dispose();
	}

	@Override
	public void addAttribute(String key, Object value) {
		framework.addAttribute(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return framework.getAttribute(key);
	}

	@Override
	public String getSessionID() {
		return sessionID;
	}

	@Override
	public FrameworkSessionInterface getFrameworkSessionInterface() {
		return this;
	}

	@Override
	public FrameworkEventInterface getFrameworkEventInterface() {
		return this;
	}

	@Override
	public void addEventConsumer(EventService.EventConsumer eventConsumer) {
		addEventConsumerForContext("", eventConsumer);
	}

	@Override
	public void addEventConsumerForContext(String context, EventService.EventConsumer eventConsumer) {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		sessionService.checkID(sessionID);

		EventService eventService = (EventService) framework.getServiceRegistry().get(EventService.class);
		eventService.addEventConsumer(sessionID, context, eventConsumer);
	}

	@Override
	public void dispatchEvent(String eventID, Object content, FrameworkPromise promise) {
		dispatchEventInContext("", eventID, content, promise);
	}

	@Override
	public void dispatchEventInContext(String context, String eventID, Object content, FrameworkPromise promise) {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		sessionService.checkID(sessionID);

		Event event = Event.build(sessionID, context, eventID, content, promise);
		EventService eventService = (EventService) framework.getServiceRegistry().get(EventService.class);
		try {
			eventService.dispatchEvent(event);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeSession() {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		sessionService.initializeSession(sessionID);
	}

	@Override
	public boolean hasFrameworkActiveSessions() {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		return sessionService.getActiveSessions() != 0;
	}

	@Override
	public void destroySession() {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		sessionService.checkID(sessionID);
		sessionService.disposeSession(sessionID);
	}
}
