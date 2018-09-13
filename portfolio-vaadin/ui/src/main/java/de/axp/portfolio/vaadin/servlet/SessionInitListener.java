package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionInitEvent;

import de.axp.framework.api.PortfolioFramework;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final PortfolioFramework framework;

	SessionInitListener(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
		event.getSession().setAttribute(PortfolioFramework.class.getSimpleName(), framework);
	}
}
