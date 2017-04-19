package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import de.axp.portfolio.framework.FrameworkFactory;
import de.axp.portfolio.framework.FrameworkInterface;

public class SessionInitListener implements com.vaadin.server.SessionInitListener {

	private final FrameworkFactory frameworkFactory;

	SessionInitListener(FrameworkFactory frameworkFactory) {
		this.frameworkFactory = frameworkFactory;
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		FrameworkInterface frameworkInterface = frameworkFactory.getFrameworkCommandInterface();
		event.getSession().setAttribute(FrameworkInterface.class.getSimpleName(), frameworkInterface);
		frameworkInterface.initSession();
	}
}
