package de.axp.portfolio.bootstrap.session;

import com.vaadin.server.SessionDestroyEvent;
import de.axp.portfolio.framework.FrameworkCommandInterface;

public class SessionDestroyListener implements com.vaadin.server.SessionDestroyListener {

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		FrameworkCommandInterface commandInterface = (FrameworkCommandInterface) event.getSession()
				.getAttribute(FrameworkCommandInterface.class.getSimpleName());
		commandInterface.deinitialize();

		System.out.println("Session destroyed");
	}
}
