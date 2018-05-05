package de.axp.portfolio.vaadin.servlet;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import de.axp.portfolio.framework.api.Framework;

import javax.servlet.ServletException;
import java.util.ArrayList;

@Push
public class PortfolioServlet extends VaadinServlet {

	private final Framework framework;

	public PortfolioServlet(Framework framework) {
		this.framework = framework;
	}

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		framework.addAttribute("sessions", new ArrayList<VaadinSession>());

		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener());
	}
}
