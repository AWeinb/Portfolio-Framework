package de.axp.portfolio.framework.internal.sessions;

import de.axp.portfolio.framework.internal.SessionManagement;

public class SessionFactory {

	public static SessionManagement createSessionManagement() {
		return new SessionManagementImpl();
	}
}
