package de.axp.portfolio.servlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import de.axp.portfolio.runtime.RuntimeBootstrap;

class SessionInitListener implements com.vaadin.server.SessionInitListener {

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		new RuntimeBootstrap();
	}
}
