package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionDestroyEvent;
import de.axp.portfolio.framework.api.GlobalFramework;

class SessionDestroyListener implements com.vaadin.flow.server.SessionDestroyListener {

	private final GlobalFramework framework;

	SessionDestroyListener(GlobalFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
	}
}
