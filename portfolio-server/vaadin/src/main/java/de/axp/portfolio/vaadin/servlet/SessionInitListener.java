package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import de.axp.portfolio.framework.FrameworkFactory;
import de.axp.portfolio.framework.FrameworkInterface;

public class SessionInitListener implements com.vaadin.server.SessionInitListener {

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		FrameworkInterface frameworkInterface = FrameworkFactory.createFrameworkCommandInterface();
		frameworkInterface.initializeSession();
		event.getSession().setAttribute(FrameworkInterface.class.getSimpleName(), frameworkInterface);
	}
}
