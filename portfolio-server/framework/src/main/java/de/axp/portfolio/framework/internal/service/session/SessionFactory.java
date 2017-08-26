package de.axp.portfolio.framework.internal.service.session;

public class SessionFactory {

	public static SessionService createSessionService() {
		return new SessionServiceImpl();
	}
}
