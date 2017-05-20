package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import de.axp.portfolio.framework.FrameworkFactory;
import de.axp.portfolio.framework.FrameworkInterface;

class SessionInitListener implements com.vaadin.server.SessionInitListener {

	private final FrameworkFactory frameworkFactory;
	private final SessionIdComputation sessionIdComputation;

	SessionInitListener(FrameworkFactory frameworkFactory, SessionIdComputation sessionIdComputation) {
		this.frameworkFactory = frameworkFactory;
		this.sessionIdComputation = sessionIdComputation;
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		FrameworkInterface frameworkInterface = frameworkFactory.getFrameworkCommandInterface();
		event.getSession().setAttribute(FrameworkInterface.class.getSimpleName(), frameworkInterface);

		String sessionId = sessionIdComputation.compute();
		event.getSession().setAttribute("ID", sessionId);

		if (!frameworkInterface.isFrameworkInitialized()) {
			frameworkInterface.initFramework();
		}
		frameworkInterface.initSession(sessionId);
	}
}
