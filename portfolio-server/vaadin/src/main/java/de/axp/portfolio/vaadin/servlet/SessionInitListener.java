package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import de.axp.portfolio.framework.FrameworkFactory;

class SessionInitListener implements com.vaadin.server.SessionInitListener {

	SessionInitListener(FrameworkFactory frameworkFactory, SessionIdComputation sessionIdComputation) {
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
	}
}
