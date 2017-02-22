package de.axp.portfolio.servlet;

import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	private final SessionInitListener sessionInitListener;
	private final SessionDestroyListener sessionDestroyListener;

	public PortfolioServlet(SessionInitListener sessionInitListener, SessionDestroyListener sessionDestroyListener) {
		this.sessionInitListener = sessionInitListener;
		this.sessionDestroyListener = sessionDestroyListener;
	}

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		getService().addSessionInitListener(sessionInitListener);
		getService().addSessionDestroyListener(sessionDestroyListener);
	}
}
