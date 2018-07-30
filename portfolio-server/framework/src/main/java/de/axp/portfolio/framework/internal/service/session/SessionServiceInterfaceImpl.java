package de.axp.portfolio.framework.internal.service.session;

import de.axp.portfolio.framework.api.FrameworkSession;
import de.axp.portfolio.framework.api.serviceinterfaces.SessionServiceInterface;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;
import de.axp.portfolio.framework.internal.service.ServiceRegistry;

class SessionServiceInterfaceImpl implements SessionServiceInterface {

	private final ServiceRegistry serviceRegistry;
	private final FrameworkSession session;

	public SessionServiceInterfaceImpl(ServiceRegistry serviceRegistry, FrameworkSession session) {
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
