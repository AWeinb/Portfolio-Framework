package de.axp.framework.internal.service.session;

public final class SessionFactory {

	public static SessionService createSessionService() {
		return new SessionServiceImpl();
	}
}
