package de.axp.portfolio.vaadin.session;

import com.vaadin.flow.server.SessionInitEvent;

import de.axp.framework.api.PortfolioFramework;

public class SessionInitListener implements com.vaadin.flow.server.SessionInitListener {

	private static final long serialVersionUID = -6198932568623699312L;

	private final PortfolioFramework framework;

	public SessionInitListener(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionInit(SessionInitEvent event) {
		event.getSession().setAttribute(PortfolioFramework.class, framework);
	}
}
