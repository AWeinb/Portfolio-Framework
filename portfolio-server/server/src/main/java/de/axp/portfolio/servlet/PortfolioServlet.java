package de.axp.portfolio.servlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.ui.PortfolioUI;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "PortfolioServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = PortfolioUI.class, productionMode = false)
public class PortfolioServlet extends VaadinServlet {
}
