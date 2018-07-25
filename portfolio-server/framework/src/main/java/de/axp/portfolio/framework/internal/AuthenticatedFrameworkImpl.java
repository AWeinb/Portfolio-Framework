package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;
import de.axp.portfolio.framework.internal.service.event.Event;
import de.axp.portfolio.framework.internal.service.event.EventService;
import de.axp.portfolio.framework.internal.service.session.SessionService;

class AuthenticatedFrameworkImpl implements AuthenticatedFramework, FrameworkEventInterface {

	private final ServiceRegistry serviceRegistry;
	private final FrameworkSession session;

	AuthenticatedFrameworkImpl(ServiceRegistry serviceRegistry, FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public FrameworkSession getSession() {
		return session;
	}

	@Override
	public FrameworkEventInterface getFrameworkEventInterface() {
		return this;
	}

	@Override
	public void invalidate(FrameworkSession session) {
		InternalFrameworkService internalFrameworkService = serviceRegistry.get(SessionService.class);
		SessionService sessionService = (SessionService) internalFrameworkService;
		sessionService.invalidateSession(session);
	}

	@Override
	public void addListener(EventListener listener) {
		addListener("", listener);
	}

	@Override
	public void addListener(String context, EventListener listener) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		EventService listenerService = (EventService) serviceRegistry.get(EventService.class);
		listenerService.register(session.toString(), context, listener);
	}

	@Override
	public void fireEvent(String eventID, Object content, EventPromise promise) {
		fireEvent("", eventID, content, promise);
	}

	@Override
	public void fireEvent(String context, String eventID, Object content, EventPromise promise) {
		SessionService sessionService = (SessionService) serviceRegistry.get(SessionService.class);
		sessionService.checkSession(session);

		Event event = Event.build(eventID, content);
		EventService eventService = (EventService) serviceRegistry.get(EventService.class);
		eventService.dispatch(session.toString(), context, event, promise);
	}
}
