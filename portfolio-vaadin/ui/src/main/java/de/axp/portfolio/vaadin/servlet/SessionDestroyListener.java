package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionDestroyEvent;
import de.axp.framework.api.BasePortfolioFramework;

class SessionDestroyListener implements com.vaadin.flow.server.SessionDestroyListener {

	private final BasePortfolioFramework framework;

	SessionDestroyListener(BasePortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
	}
}
