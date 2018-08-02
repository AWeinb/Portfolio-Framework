package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.VaadinServlet;
import de.axp.framework.api.PortfolioFramework;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework.BasePortfolioFramework framework = PortfolioFramework.createBaseFramework();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));
	}
}
