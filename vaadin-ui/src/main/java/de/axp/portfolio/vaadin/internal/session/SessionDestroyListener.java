package de.axp.portfolio.vaadin.internal.session;

import com.vaadin.flow.server.SessionDestroyEvent;

import de.axp.framework.api.PortfolioFramework;

public class SessionDestroyListener implements com.vaadin.flow.server.SessionDestroyListener {

	private static final long serialVersionUID = 8378024690438022494L;

	private final PortfolioFramework framework;

	public SessionDestroyListener(PortfolioFramework framework) {
		this.framework = framework;
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
	}
}
