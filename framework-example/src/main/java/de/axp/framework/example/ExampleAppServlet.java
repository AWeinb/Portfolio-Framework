package de.axp.framework.example;

import javax.servlet.ServletException;

import com.vaadin.flow.server.VaadinServlet;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.example.session.SessionDestroyListener;
import de.axp.framework.example.session.SessionInitListener;

public class ExampleAppServlet extends VaadinServlet {

	private static final long serialVersionUID = -713474004104403527L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework framework = PortfolioFramework.createFramework();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));

		framework.getTranslationService().registerTranslator(new ExampleTranslator());

		PortfolioService portfolioService = framework.getPortfolioService();
		portfolioService.registerPortfolioDefinition(new ExamplePortfolioDefinition());
	}
}
