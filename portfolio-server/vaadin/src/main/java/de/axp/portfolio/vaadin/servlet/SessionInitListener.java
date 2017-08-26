package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.SessionFramework;

class SessionInitListener implements com.vaadin.server.SessionInitListener {

	private final Framework framework;

	SessionInitListener(Framework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		String sessionID = event.getSession().getSession().getId();
		SessionFramework sessionFramework = framework.adaptForSession(sessionID);
		sessionFramework.getFrameworkSessionInterface().initializeSession();

		event.getSession().setAttribute(Framework.class.getName(), sessionFramework);
	}
}
