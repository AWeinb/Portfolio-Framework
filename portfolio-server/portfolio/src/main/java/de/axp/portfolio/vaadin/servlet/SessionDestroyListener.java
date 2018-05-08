package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionDestroyEvent;
import de.axp.portfolio.framework.api.Framework;

class SessionDestroyListener implements com.vaadin.flow.server.SessionDestroyListener {

	private final Framework framework;

	SessionDestroyListener(Framework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
	}
}
