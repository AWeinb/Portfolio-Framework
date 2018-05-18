package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.VaadinServlet;
import de.axp.portfolio.framework.api.GlobalFramework;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		GlobalFramework framework = GlobalFramework.create();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));
	}
}
