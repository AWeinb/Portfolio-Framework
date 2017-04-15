package de.axp.portfolio.vaadin.servlet;

import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	private final SessionInitListener sessionInitListener;
	private final SessionDestroyListener sessionDestroyListener;

	public PortfolioServlet() {
		sessionInitListener = new SessionInitListener();
		sessionDestroyListener = new SessionDestroyListener();
	}

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		getService().addSessionInitListener(sessionInitListener);
		getService().addSessionDestroyListener(sessionDestroyListener);
	}
}
