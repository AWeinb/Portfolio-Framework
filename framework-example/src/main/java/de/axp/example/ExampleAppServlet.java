package de.axp.example;

import de.axp.example.portfolio.ExamplePortfolioDefinition;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.PortfolioVaadinServlet;

import javax.servlet.ServletException;

public class ExampleAppServlet extends PortfolioVaadinServlet {

	private static final long serialVersionUID = -713474004104403527L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework frameworkInstance = getFrameworkInstance();
		UiService uiService = frameworkInstance.getUiService();
		uiService.registerPortfolioDefinition(new ExamplePortfolioDefinition());
	}
}
