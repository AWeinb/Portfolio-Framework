package de.axp.portfolio.servlet;

import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		getService().addSessionInitListener(new SessionInitListener());
		getService().addSessionDestroyListener(new SessionDestroyListener());
	}
}
