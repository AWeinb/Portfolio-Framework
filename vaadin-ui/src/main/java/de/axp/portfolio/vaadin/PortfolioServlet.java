package de.axp.portfolio.vaadin;

import com.vaadin.flow.server.VaadinServlet;
import de.axp.framework.api.PortfolioFramework;
import de.axp.portfolio.vaadin.services.ui.UiServiceFactory;
import de.axp.portfolio.vaadin.services.ui.api.UiService;
import de.axp.portfolio.vaadin.session.SessionDestroyListener;
import de.axp.portfolio.vaadin.session.SessionInitListener;

import javax.servlet.ServletException;

public class PortfolioServlet extends VaadinServlet {

	private static final long serialVersionUID = -713474004104403527L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework framework = PortfolioFramework.createFramework();
		framework.getServiceService().registerNewService(UiService.class, UiServiceFactory.createUiService(framework));

		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));
	}
}
