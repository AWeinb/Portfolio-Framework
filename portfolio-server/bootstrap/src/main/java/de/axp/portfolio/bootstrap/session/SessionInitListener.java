package de.axp.portfolio.bootstrap.session;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;

public class SessionInitListener implements com.vaadin.server.SessionInitListener {

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		System.out.println("Session initialized");
	}
}
