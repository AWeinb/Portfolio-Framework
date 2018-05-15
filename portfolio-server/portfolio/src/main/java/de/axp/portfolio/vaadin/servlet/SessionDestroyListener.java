package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.SessionDestroyEvent;
import de.axp.portfolio.framework.api.BaseFramework;

class SessionDestroyListener implements com.vaadin.flow.server.SessionDestroyListener {

	private final BaseFramework framework;

	SessionDestroyListener(BaseFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
	}
}
