package de.axp.portfolio.servlet;

import com.vaadin.server.SessionDestroyEvent;

class SessionDestroyListener implements com.vaadin.server.SessionDestroyListener {

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		System.out.println("Session destroy");
	}
}
