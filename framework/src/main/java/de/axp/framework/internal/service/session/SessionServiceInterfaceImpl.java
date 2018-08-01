package de.axp.framework.internal.service.session;

import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.framework.internal.service.InternalFrameworkService;
import de.axp.framework.internal.service.ServiceRegistry;

class SessionServiceInterfaceImpl implements SessionServiceInterface {

	private final ServiceRegistry serviceRegistry;
	private final FrameworkSession session;

	SessionServiceInterfaceImpl(ServiceRegistry serviceRegistry, FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public FrameworkSession getSession() {
		return session;
	}

	@Override
	public void invalidate() {
		InternalFrameworkService internalFrameworkService = serviceRegistry.get(SessionService.class);
		SessionService sessionService = (SessionService) internalFrameworkService;
		sessionService.invalidateSession(session);
	}
}
