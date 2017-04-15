package de.axp.portfolio.vaadin;

import com.vaadin.server.VaadinServlet;
import de.axp.portfolio.vaadin.servlet.PortfolioServlet;
import de.axp.portfolio.vaadin.ui.PortfolioUIProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.LifeCycle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PortfolioServerInitializerTest {

	private TestServer testServer;
	private PortfolioServerInitializer portfolioServerInitializer;
	private ServletHolder servletHolder;

	@Before
	public void setUp() throws Exception {
		testServer = new TestServer();
		servletHolder = new ServletHolder(new PortfolioServlet());
		portfolioServerInitializer = new PortfolioServerInitializer();
	}

	@Test
	public void shouldSetInfrastructureInstances() throws Exception {
		portfolioServerInitializer.initialize(testServer, servletHolder);

		assertNotNull(testServer.getSessionIdManager());
		assertNotNull(testServer.getHandler());
	}

	@Test
	public void checkServletHolderSetup() throws Exception {
		portfolioServerInitializer.initialize(testServer, servletHolder);

		assertEquals(testServer, servletHolder.getServletHandler().getServer());
		assertEquals(PortfolioUIProvider.class.getName(), servletHolder.getInitParameter(VaadinServlet.SERVLET_PARAMETER_UI_PROVIDER));
	}

	private class TestServer extends Server {

		@Override
		protected void start(LifeCycle l) throws Exception {
		}
	}
}