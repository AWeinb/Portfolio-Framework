package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionInitEvent;
import de.axp.portfolio.framework.api.BaseFramework;

class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private final BaseFramework framework;

	SessionInitListener(BaseFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
	}
}
