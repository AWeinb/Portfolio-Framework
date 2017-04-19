package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.SessionDestroyEvent;
import de.axp.portfolio.framework.FrameworkInterface;

public class SessionDestroyListener implements com.vaadin.server.SessionDestroyListener {

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		String attributeId = FrameworkInterface.class.getSimpleName();
		FrameworkInterface frameworkInterface = (FrameworkInterface) event.getSession().getAttribute(attributeId);
		event.getSession().setAttribute(FrameworkInterface.class.getSimpleName(), null);
		frameworkInterface.destroySession();
	}
}
