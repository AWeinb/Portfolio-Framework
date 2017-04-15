package de.axp.portfolio;

import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.vaadin.PortfolioServerInitializer;
import de.axp.portfolio.vaadin.servlet.PortfolioServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		VaadinServlet vaadinServlet = new PortfolioServlet();
		ServletHolder servletHolder = new ServletHolder(vaadinServlet);
		PortfolioServerInitializer portfolioServerInitializer = new PortfolioServerInitializer();
		portfolioServerInitializer.initialize(server, servletHolder);

		server.start();
		server.join();
	}
}
