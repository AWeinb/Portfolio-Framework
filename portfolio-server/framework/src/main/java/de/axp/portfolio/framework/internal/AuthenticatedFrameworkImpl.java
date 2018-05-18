package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.GlobalFramework;
import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.event.EventService;
import de.axp.portfolio.framework.internal.service.session.SessionService;

import static de.axp.portfolio.framework.internal.service.event.EventService.Event;

class AuthenticatedFrameworkImpl implements AuthenticatedFramework, FrameworkEventInterface, FrameworkSessionInterface {

	private final GlobalFramework framework;
	private final ServiceRegistry serviceRegistry;
	private final FrameworkSession session;

	AuthenticatedFrameworkImpl(GlobalFramework framework, ServiceRegistry serviceRegistry, FrameworkSession session) {
		this.framework = framework;
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public void dispose() {
		framework.dispose();
	}

	@Override
	public FrameworkSession getSession() {
		return session;
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
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkID(session.toString());

		EventService eventService = (EventService) serviceRegistry.get(EventService.class);
		eventService.addEventConsumer(session.toString(), context, eventConsumer);
	}

	@Override
	public void dispatchEvent(String eventID, Object content, FrameworkPromise promise) {
		dispatchEventInContext("", eventID, content, promise);
	}

	@Override
	public void dispatchEventInContext(String context, String eventID, Object content, FrameworkPromise promise) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkID(session.toString());

		Event event = Event.build(session.toString(), context, eventID, content, promise);
		EventService eventService = (EventService) serviceRegistry.get(EventService.class);
		try {
			eventService.dispatchEvent(event);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeSession() {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.initializeSession(session.toString());
	}

	@Override
	public boolean hasFrameworkActiveSessions() {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		return sessionService.getActiveSessions() != 0;
	}

	@Override
	public void destroySession() {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkID(session.toString());
		sessionService.disposeSession(session.toString());
	}
}
