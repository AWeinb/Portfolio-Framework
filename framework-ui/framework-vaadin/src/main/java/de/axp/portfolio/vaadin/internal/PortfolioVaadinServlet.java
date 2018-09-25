package de.axp.portfolio.vaadin.internal;

import javax.servlet.ServletException;

import com.vaadin.flow.server.VaadinServlet;

import de.axp.framework.api.PortfolioFramework;
import de.axp.portfolio.vaadin.api.services.UiService;
import de.axp.portfolio.vaadin.internal.services.ui.UiServiceFactory;
import de.axp.portfolio.vaadin.internal.session.SessionDestroyListener;
import de.axp.portfolio.vaadin.internal.session.SessionInitListener;

public abstract class PortfolioVaadinServlet extends VaadinServlet {

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
