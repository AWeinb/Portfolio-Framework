package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.framework.api.FrameworkThreadSynchronizer;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.internal.authentication.Authentication;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final PortfolioFramework.BasePortfolioFramework framework;

	SessionInitListener(PortfolioFramework.BasePortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
		FrameworkThreadSynchronizer synchronization = runnable -> event.getSession().access(runnable::run);
		Authentication authentication = new Authentication(event.getSession().getCsrfToken());
		PortfolioFramework authenticatedFramework = framework.adaptToAuthentication(authentication, synchronization);
		event.getSession().setAttribute(PortfolioFramework.class.getSimpleName(), authenticatedFramework);
	}
}
