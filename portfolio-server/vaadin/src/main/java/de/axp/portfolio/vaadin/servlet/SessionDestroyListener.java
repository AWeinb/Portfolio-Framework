package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.VaadinSession;
import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.SessionFramework;

import java.util.List;

class SessionDestroyListener implements com.vaadin.server.SessionDestroyListener {

	SessionDestroyListener() {
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		Object attribute = event.getSession().getAttribute(Framework.class.getName());
		SessionFramework sessionFramework = (SessionFramework) attribute;
		List<VaadinSession> sessions = (List<VaadinSession>) sessionFramework.getAttribute("sessions");
		sessions.remove(event.getSession());

		sessionFramework.getFrameworkSessionInterface().destroySession();
	}
}
