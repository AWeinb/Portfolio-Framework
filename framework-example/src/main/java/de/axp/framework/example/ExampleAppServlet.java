package de.axp.framework.example;

import javax.servlet.ServletException;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.example.vaadin.VaadinFrameworkServlet;

public class ExampleAppServlet extends VaadinFrameworkServlet {

	private static final long serialVersionUID = -713474004104403527L;

	@Override
	protected void servletInitialized(PortfolioFramework framework) throws ServletException {
		PortfolioService portfolioService = framework.getPortfolioService();
		portfolioService.registerPortfolioDefinition(new ExamplePortfolioDefinition());
	}
}
