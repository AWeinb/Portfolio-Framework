package de.axp.portfolio.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.portfolio.framework.api.AuthenticatedPortfolioFramework;
import de.axp.portfolio.framework.api.PortfolioFramework;
import de.axp.portfolio.framework.api.UserSessionAccessor;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final PortfolioFramework framework;

	SessionInitListener(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
		AuthenticatedPortfolioFramework authenticatedFramework = framework.authenticate(event.getSession().getCsrfToken());
		UserSessionAccessor accessor = runnable -> event.getSession().access(runnable::run);
		authenticatedFramework.setUserSessionAccessor(accessor);
		event.getSession().setAttribute(AuthenticatedPortfolioFramework.class.getSimpleName(), authenticatedFramework);
	}
}
