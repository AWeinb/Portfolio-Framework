package de.axp.framework.internal.services.session;

import de.axp.framework.api.services.SessionService;
import de.axp.framework.internal.services.BaseServiceRegistry;

class SessionServiceImpl implements SessionService {

	private final BaseServiceRegistry serviceRegistry;
	private final FrameworkSession session;

	SessionServiceImpl(BaseServiceRegistry serviceRegistry, FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.session = session;
	}

	@Override
	public FrameworkSession getSession() {
		return session;
	}

	@Override
	public void invalidate() {
		BaseSessionService internalSessionService = serviceRegistry.getBaseService(BaseSessionService.class);
		internalSessionService.invalidateSession(session);
	}
}
