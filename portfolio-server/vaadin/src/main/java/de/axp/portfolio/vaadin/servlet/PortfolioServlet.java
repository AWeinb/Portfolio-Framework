package de.axp.portfolio.vaadin.servlet;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.framework.api.Framework;

import javax.servlet.ServletException;

@Push
public class PortfolioServlet extends VaadinServlet {

	private final Framework framework;

	public PortfolioServlet(Framework framework) {
		this.framework = framework;
	}

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener());
	}
}
