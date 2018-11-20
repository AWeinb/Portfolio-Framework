package de.axp.framework.example.vaadin;

import javax.servlet.ServletException;

import com.vaadin.flow.server.VaadinServlet;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.example.vaadin.internal.session.SessionDestroyListener;
import de.axp.framework.example.vaadin.internal.session.SessionInitListener;

public abstract class VaadinFrameworkServlet extends VaadinServlet {

	private static final long serialVersionUID = -713474004104403527L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		PortfolioFramework framework = PortfolioFramework.createFramework();
		getService().addSessionInitListener(new SessionInitListener(framework));
		getService().addSessionDestroyListener(new SessionDestroyListener(framework));

		framework.getTranslationService().registerTranslator(new VaadinFrameworkTranslator());

		servletInitialized(framework);
	}

	protected abstract void servletInitialized(PortfolioFramework framework) throws ServletException;

}
