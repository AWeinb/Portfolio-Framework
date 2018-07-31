package de.axp.portfolio.servlet;

import com.vaadin.flow.server.VaadinServlet;
import de.axp.portfolio.framework.api.PortfolioFramework;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework framework = PortfolioFramework.create();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));
	}
}
