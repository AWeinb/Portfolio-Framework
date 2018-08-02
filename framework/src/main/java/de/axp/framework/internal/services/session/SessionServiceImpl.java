package de.axp.framework.internal.services.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.services.InternalFrameworkService;
import de.axp.framework.internal.services.ServiceRegistry;

class SessionServiceImpl implements SessionService {

	private final ServiceRegistry serviceRegistry;
	private final FrameworkSession session;

	SessionServiceImpl(ServiceRegistry serviceRegistry, FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public FrameworkSession getSession() {
		return session;
	}

	@Override
	public void invalidate() {
		InternalFrameworkService service = serviceRegistry.get(InternalSessionService.class);
		InternalSessionService internalFrameworkService = (InternalSessionService) service;
		internalFrameworkService.invalidateSession(session);
	}
}
