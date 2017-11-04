package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.VaadinSession;
import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.SessionFramework;

import java.util.List;

class SessionInitListener implements com.vaadin.server.SessionInitListener {

	private final Framework framework;

	SessionInitListener(Framework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		List<VaadinSession> sessions = (List<VaadinSession>) framework.getAttribute("sessions");
		sessions.add(event.getSession());

		String sessionID = event.getSession().getSession().getId();
		SessionFramework sessionFramework = framework.adaptForSession(sessionID);
		sessionFramework.getFrameworkSessionInterface().initializeSession();

		event.getSession().setAttribute(Framework.class.getName(), sessionFramework);
	}
}
