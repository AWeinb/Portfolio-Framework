package de.axp.portfolio.vaadin.servlet;

import javax.servlet.ServletException;

import com.vaadin.flow.server.VaadinServlet;

import de.axp.framework.api.PortfolioFramework;

public class PortfolioServlet extends VaadinServlet {

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework framework = PortfolioFramework.createFramework();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));
	}
}
