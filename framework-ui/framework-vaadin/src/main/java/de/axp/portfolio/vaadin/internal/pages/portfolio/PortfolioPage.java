package de.axp.portfolio.vaadin.internal.pages.portfolio;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.FallbackPortfolioDefinition;
import de.axp.portfolio.vaadin.internal.pages.QueryParametersUtil;
import de.axp.portfolio.vaadin.internal.pages.portfolio.content.PortfolioContentManager;
import de.axp.portfolio.vaadin.internal.pages.portfolio.navigation.PortfolioNavigationManager;

import static de.axp.framework.api.services.UiService.PortfolioDefinition;
import static de.axp.framework.api.services.UiService.PortfolioPart;

@Route(value = "portfolio")
@StyleSheet("frontend://styles/portfolio.css")
public class PortfolioPage extends Div implements HasUrlParameter<String>, AfterNavigationObserver {

	private static final long serialVersionUID = 3217286454817153835L;

	private final PortfolioFramework framework;
	private final PortfolioPageState state;
	private final PortfolioNavigationManager navigationManager;
	private final PortfolioContentManager contentManager;
	private final FallbackPortfolioDefinition fallbackPortfolioDefinition;

	{
		framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		state = new PortfolioPageState();
		navigationManager = new PortfolioNavigationManager(state);
		contentManager = new PortfolioContentManager(state);
		fallbackPortfolioDefinition = new FallbackPortfolioDefinition(framework);

		state.setPortfolioDefinition(fallbackPortfolioDefinition);

		setClassName("portfolio");
		add(navigationManager.getNavigationComponent());
		add(contentManager.getContentComponent());
	}

	@Override
	public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
		QueryParameters queryParameters = event.getLocation().getQueryParameters();
		String portfolioId = QueryParametersUtil.extractPortfolioId(queryParameters);
		String partId = QueryParametersUtil.extractPartId(queryParameters);

		UiService uiService = framework.getServiceByType(UiService.class);

		PortfolioDefinition portfolioDefinition = state.getPortfolioDefinition();
		if (!portfolioDefinition.getPortfolioId().equals(portfolioId)) {
			portfolioDefinition = uiService.getPortfolioDefinitions().stream() //
					.filter(d -> d.getPortfolioId().equals(portfolioId)) //
					.findFirst() //
					.orElse(fallbackPortfolioDefinition);
			state.setPortfolioDefinition(portfolioDefinition);
		}

		PortfolioPart portfolioPart = portfolioDefinition.getPortfolioParts().get(state.getPortfolioPartIndex());
		if (!portfolioPart.getPartId().equals(partId)) {
			for (int i = 0; i < portfolioDefinition.getPortfolioParts().size(); i++) {
				PortfolioPart part = portfolioDefinition.getPortfolioParts().get(i);
				if (partId.equals(part.getPartId())) {
					state.setPortfolioPartIndex(i);
					break;
				}
			}
		}
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		navigationManager.update();
		contentManager.update();
	}
}
