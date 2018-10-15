package de.axp.portfolio.vaadin.internal.pages.portfolio;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.framework.api.services.UiService.PortfolioDefinition;
import de.axp.framework.api.services.UiService.PortfolioPart;
import de.axp.portfolio.vaadin.internal.pages.portfolio.content.ContentLayout;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.PortfolioNavigationManager;

import java.util.List;
import java.util.Optional;

@Route(value = "portfolio")
@StyleSheet("frontend://styles/portfolio.css")
public class PortfolioPage extends Div implements HasUrlParameter<String>, AfterNavigationObserver {

	private static final long serialVersionUID = 3217286454817153835L;

	private final UiService uiService;
	private final UrlStateManager urlStateManager;
	private final PortfolioNavigationManager navigationManager;
	private final ContentLayout contentLayout = new ContentLayout();

	{
		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		uiService = framework.getServiceByType(UiService.class);
		urlStateManager = new UrlStateManager(uiService);
		navigationManager = new PortfolioNavigationManager(urlStateManager.getUrlState());

		setClassName("portfolio");
		add(navigationManager.getNavigationComponent());
		add(contentLayout);
	}

	@Override
	public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
		urlStateManager.updateRootSegment(event.getLocation().getFirstSegment());
		urlStateManager.updateParameterSegment(parameter);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		navigationManager.updateNavigationComponent();
		contentLayout.removeAll();
		contentLayout.add(getPortfolioPartComponent());
	}

	private Component getPortfolioPartComponent() {
		String pageSegment = urlStateManager.getUrlState().getPageSegment();
		Optional<PortfolioDefinition> portfolioDefinition = uiService.getPortfolioDefinition(pageSegment);
		PortfolioDefinition currentDefinition = portfolioDefinition.orElse(new FallbackPortfolioDefinition());
		List<? extends PortfolioPart> portfolioParts = currentDefinition.getPortfolioParts();
		PortfolioPart<Component> portfolioPart = portfolioParts.get(urlStateManager.getUrlState().getPartIndex());
		return portfolioPart.getUiComponent();
	}
}
