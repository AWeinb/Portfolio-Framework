package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionDestroyEvent;
import de.axp.framework.api.PortfolioFramework;

class SessionDestroyListener implements com.vaadin.flow.server.SessionDestroyListener {

	private final PortfolioFramework.BasePortfolioFramework framework;

	SessionDestroyListener(PortfolioFramework.BasePortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
	}
}
