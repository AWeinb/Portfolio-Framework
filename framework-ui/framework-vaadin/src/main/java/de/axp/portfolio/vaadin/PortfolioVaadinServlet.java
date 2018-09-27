package de.axp.portfolio.vaadin;

import com.vaadin.flow.server.VaadinServlet;
import de.axp.framework.api.PortfolioFramework;
import de.axp.portfolio.vaadin.internal.session.SessionDestroyListener;
import de.axp.portfolio.vaadin.internal.session.SessionInitListener;

import javax.servlet.ServletException;

public abstract class PortfolioVaadinServlet extends VaadinServlet {

	private static final long serialVersionUID = -713474004104403527L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework framework = PortfolioFramework.createFramework();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));
	}
}
