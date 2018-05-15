package de.axp.portfolio.vaadin.servlet;

import com.vaadin.flow.server.VaadinServlet;
import de.axp.portfolio.framework.api.BaseFramework;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		BaseFramework framework = BaseFramework.create();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));
	}
}
