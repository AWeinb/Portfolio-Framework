package de.axp.portfolio;

import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.servlet.PortfolioServlet;
import de.axp.portfolio.bootstrap.session.SessionDestroyListener;
import de.axp.portfolio.bootstrap.session.SessionInitListener;
import de.axp.portfolio.ui.PortfolioUIProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		DefaultSessionIdManager idmanager = new DefaultSessionIdManager(server);
		server.setSessionIdManager(idmanager);

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(servletContextHandler);

		SessionInitListener sessionInitListener = new SessionInitListener();
		SessionDestroyListener sessionDestroyListener = new SessionDestroyListener();
		VaadinServlet vaadinServlet = new PortfolioServlet(sessionInitListener, sessionDestroyListener);
		ServletHolder servletHolder = new ServletHolder(vaadinServlet);
		servletHolder.setInitParameter(VaadinServlet.SERVLET_PARAMETER_UI_PROVIDER, PortfolioUIProvider.class.getName());

		servletContextHandler.addServlet(servletHolder, "/*");
		servletContextHandler.setServer(server);

		server.start();
		server.join();
	}
}
