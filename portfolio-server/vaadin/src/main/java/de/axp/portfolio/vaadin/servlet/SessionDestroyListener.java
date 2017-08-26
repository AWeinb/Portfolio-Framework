package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionDestroyEvent;
import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.SessionFramework;

class SessionDestroyListener implements com.vaadin.server.SessionDestroyListener {

	SessionDestroyListener() {
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		Object attribute = event.getSession().getAttribute(Framework.class.getName());
		SessionFramework sessionFramework = (SessionFramework) attribute;

		sessionFramework.getFrameworkSessionInterface().destroySession();
	}
}
