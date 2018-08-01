package de.axp.framework.internal.service.interfaces;

import de.axp.framework.api.FrameworkSession;
import de.axp.framework.api.services.SessionServiceInterface;
import de.axp.framework.internal.service.InternalFrameworkService;
import de.axp.framework.internal.service.ServiceRegistry;
import de.axp.framework.internal.service.session.InternalSessionService;

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
		InternalFrameworkService service = serviceRegistry.get(InternalSessionService.class);
		InternalSessionService internalFrameworkService = (InternalSessionService) service;
		internalFrameworkService.invalidateSession(session);
	}
}
