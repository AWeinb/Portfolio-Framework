package de.axp.portfolio.bootstrap.session;

import com.vaadin.server.SessionDestroyEvent;

public class SessionDestroyListener implements com.vaadin.server.SessionDestroyListener {

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		System.out.println("Session destroyed");
	}
}
