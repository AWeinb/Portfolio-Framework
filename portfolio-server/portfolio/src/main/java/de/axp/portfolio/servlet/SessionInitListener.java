package de.axp.portfolio.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.GlobalFramework;
import de.axp.portfolio.framework.api.UserSessionAccessor;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final GlobalFramework framework;

	SessionInitListener(GlobalFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
		AuthenticatedFramework authenticatedFramework = framework.authenticate(event.getSession().getCsrfToken());
		UserSessionAccessor accessor = runnable -> event.getSession().access(runnable::run);
		authenticatedFramework.setUserSessionAccessor(accessor);
		event.getSession().setAttribute(AuthenticatedFramework.class.getSimpleName(), authenticatedFramework);
	}
}
