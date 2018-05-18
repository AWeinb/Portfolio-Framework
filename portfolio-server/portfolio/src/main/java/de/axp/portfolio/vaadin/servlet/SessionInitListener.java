package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.portfolio.framework.api.GlobalFramework;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final GlobalFramework framework;

	SessionInitListener(GlobalFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
	}
}
