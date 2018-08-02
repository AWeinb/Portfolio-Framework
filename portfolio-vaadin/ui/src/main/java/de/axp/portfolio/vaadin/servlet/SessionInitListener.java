package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.FrameworkThreadSynchronizer;
import de.axp.framework.api.BasePortfolioFramework;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final BasePortfolioFramework framework;

	SessionInitListener(BasePortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
		PortfolioFramework authenticatedFramework = framework.authenticate(
				event.getSession().getCsrfToken());
		FrameworkThreadSynchronizer synchronization = runnable -> event.getSession().access(runnable::run);
		authenticatedFramework.setMainThreadSynchronization(synchronization);
		event.getSession().setAttribute(PortfolioFramework.class.getSimpleName(), authenticatedFramework);
	}
}
