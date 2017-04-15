package de.axp.portfolio.vaadin;

import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.vaadin.ui.PortfolioUIProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class PortfolioServerInitializer {

	public void initialize(Server server, ServletHolder servletHolder) throws Exception {
		DefaultSessionIdManager idManager = new DefaultSessionIdManager(server);
		server.setSessionIdManager(idManager);

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(servletContextHandler);
		servletHolder.setInitParameter(VaadinServlet.SERVLET_PARAMETER_UI_PROVIDER, PortfolioUIProvider.class.getName());
		servletContextHandler.addServlet(servletHolder, "/*");
		servletContextHandler.setServer(server);
		server.setHandler(servletContextHandler);
	}
}
