package de.axp.framework.internal.service.session;

public final class SessionFactory {

	public static InternalSessionService createInternalSessionService() {
		return new InternalSessionServiceImpl();
	}
}
