package de.axp.portfolio;

import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.FrameworkExtensions;
import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.event.EventService;
import de.axp.portfolio.vaadin.servlet.PortfolioServlet;
import de.axp.portfolio.vaadin.ui.PortfolioUIProvider;
import org.atmosphere.container.Jetty9AsyncSupportWithWebSocket;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

class Main {

	public static void main(String[] args) throws Exception {
		FrameworkExtensions frameworkExtensions = new FrameworkExtensions();
		EventService.EventHandler eventHandler = getCommandHandler();
		frameworkExtensions.setEventHandler(eventHandler);
		Framework framework = Framework.create(frameworkExtensions);

		eventHandler.setFrameworkReference(framework);

		Server server = new Server(8080);
		VaadinServlet vaadinServlet = new PortfolioServlet(framework);
		ServletHolder servletHolder = new ServletHolder(vaadinServlet);
		initializeServer(server, servletHolder);

		server.start();
		server.join();
	}

	private static EventService.EventHandler getCommandHandler() {
		return new EventService.EventHandler() {

			@Override
			public void setFrameworkReference(Framework framework) {
			}

			@Override
			public void execute(String sessionID, String commandID, Object content,
			                    FrameworkPromise promiseToResolveOrReject) {
				promiseToResolveOrReject.setFutureOutput("Got: " + content);
				promiseToResolveOrReject.resolve();
			}
		};
	}

	private static void initializeServer(Server server, ServletHolder servletHolder) {
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(servletContextHandler);

		setUiParameter(servletHolder);
		setWebsocketParameter(servletHolder);

		servletContextHandler.addServlet(servletHolder, "/*");
		servletContextHandler.setServer(server);
		server.setHandler(servletContextHandler);
	}

	private static void setUiParameter(ServletHolder servletHolder) {
		String param = VaadinServlet.SERVLET_PARAMETER_UI_PROVIDER;
		String value = PortfolioUIProvider.class.getName();
		servletHolder.setInitParameter(param, value);
	}

	private static void setWebsocketParameter(ServletHolder servletHolder) {
		String param = "org.atmosphere.cpr.asyncSupport";
		String value = Jetty9AsyncSupportWithWebSocket.class.getName();
		servletHolder.setInitParameter(param, value);
	}
}
