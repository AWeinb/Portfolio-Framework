package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.FrameworkThreadSynchronizer;
import de.axp.framework.api.PortfolioFramework;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final PortfolioFramework framework;

	SessionInitListener(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
		AuthenticatedPortfolioFramework authenticatedFramework = framework.authenticate(
				event.getSession().getCsrfToken());
		FrameworkThreadSynchronizer synchronization = runnable -> event.getSession().access(runnable::run);
		authenticatedFramework.setMainThreadSynchronization(synchronization);
		event.getSession().setAttribute(AuthenticatedPortfolioFramework.class.getSimpleName(), authenticatedFramework);
	}
}
