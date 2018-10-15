package de.axp.portfolio.vaadin.internal.pages.portfolio;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.pages.portfolio.content.PortfolioContentManager;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.PortfolioNavigationManager;

@Route(value = "portfolio")
@StyleSheet("frontend://styles/portfolio.css")
public class PortfolioPage extends Div implements HasUrlParameter<String>, AfterNavigationObserver {

	private static final long serialVersionUID = 3217286454817153835L;

	private final UrlStateManager urlStateManager;
	private final PortfolioNavigationManager navigationManager;
	private final PortfolioContentManager contentManager;

	{
		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		UiService uiService = framework.getServiceByType(UiService.class);
		urlStateManager = new UrlStateManager(uiService);
		navigationManager = new PortfolioNavigationManager(urlStateManager.getUrlState());
		contentManager = new PortfolioContentManager(uiService, urlStateManager.getUrlState());

		setClassName("portfolio");
		add(navigationManager.getNavigationComponent());
		add(contentManager.getContentComponent());
	}

	@Override
	public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
		urlStateManager.updateRootSegment(event.getLocation().getFirstSegment());
		urlStateManager.updateParameterSegment(parameter);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		navigationManager.updateNavigationComponent();
		contentManager.updateContentComponent();
	}
}
