package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionDestroyEvent;
import de.axp.portfolio.framework.FrameworkInterface;

public class SessionDestroyListener implements com.vaadin.server.SessionDestroyListener {

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		String interfaceId = FrameworkInterface.class.getSimpleName();
		FrameworkInterface commandInterface = (FrameworkInterface) event.getSession().getAttribute(interfaceId);
		commandInterface.destroySession();
	}
}
