package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.portfolio.framework.api.Framework;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final Framework framework;

	SessionInitListener(Framework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
	}
}
