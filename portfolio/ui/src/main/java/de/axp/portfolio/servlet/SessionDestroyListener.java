package de.axp.portfolio.servlet;

import com.vaadin.flow.server.SessionDestroyEvent;
import de.axp.portfolio.framework.api.PortfolioFramework;

class SessionDestroyListener implements com.vaadin.flow.server.SessionDestroyListener {

	private final PortfolioFramework framework;

	SessionDestroyListener(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
	}
}
