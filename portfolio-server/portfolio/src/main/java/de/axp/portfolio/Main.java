package de.axp.portfolio;

import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.interaction.FrameworkExtensions;
import de.axp.portfolio.framework.api.interaction.FrameworkPromise;
import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.ui.UiService;
import de.axp.portfolio.vaadin.servlet.PortfolioServlet;
import de.axp.portfolio.vaadin.ui.PortfolioUIProvider;
import de.axp.portfolio.vaadin.ui.UiChangeHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

class Main {

	public static void main(String[] args) throws Exception {
		FrameworkExtensions frameworkExtensions = new FrameworkExtensions();
		CommandService.CommandHandler commandHandler = getCommandHandler();
		UiService.UiChangeHandler uiChangeHandler = getUiChangeHandler();
		frameworkExtensions.setCommandHandler(commandHandler);
		frameworkExtensions.setUiChangeHandler(uiChangeHandler);
		Framework framework = Framework.create(frameworkExtensions);

		commandHandler.setFrameworkReference(framework);
		uiChangeHandler.setFrameworkReference(framework);

		Server server = new Server(8080);
		VaadinServlet vaadinServlet = new PortfolioServlet(framework);
		ServletHolder servletHolder = new ServletHolder(vaadinServlet);
		initializeServer(server, servletHolder);

		server.start();
		server.join();
	}

	private static void initializeServer(Server server, ServletHolder servletHolder) {
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(servletContextHandler);
		servletHolder.setInitParameter(VaadinServlet.SERVLET_PARAMETER_UI_PROVIDER,
				PortfolioUIProvider.class.getName());
		servletContextHandler.addServlet(servletHolder, "/*");
		servletContextHandler.setServer(server);
		server.setHandler(servletContextHandler);
	}

	private static CommandService.CommandHandler getCommandHandler() {
		return new CommandService.CommandHandler() {

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

	private static UiService.UiChangeHandler getUiChangeHandler() {
		return new UiChangeHandler();
	}
}
