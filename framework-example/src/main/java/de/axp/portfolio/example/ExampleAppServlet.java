package de.axp.portfolio.example;

import javax.servlet.ServletException;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.library.example.ExampleFrameworkExtension;
import de.axp.portfolio.example.vaadin.VaadinFrameworkServlet;

public class ExampleAppServlet extends VaadinFrameworkServlet {

	private static final long serialVersionUID = -713474004104403527L;

	@Override
	protected void servletInitialized(PortfolioFramework framework) throws ServletException {
		UiService uiService = framework.getUiService();
		uiService.registerPortfolioDefinition(new ExamplePortfolioDefinition());
		framework.install(new ExampleFrameworkExtension());
	}
}
