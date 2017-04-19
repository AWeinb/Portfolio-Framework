package de.axp.portfolio.vaadin.servlet;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.framework.FrameworkFactory;

import javax.servlet.ServletException;

@Push
public class PortfolioServlet extends VaadinServlet {

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		getService().addSessionInitListener(new SessionInitListener(FrameworkFactory.INSTANCE));
		getService().addSessionDestroyListener(new SessionDestroyListener());
	}
}
