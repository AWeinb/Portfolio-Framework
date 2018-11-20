package de.axp.framework.example.vaadin.internal.pages.portfolio;

import static de.axp.framework.api.services.PortfolioService.PortfolioDefinition;
import static de.axp.framework.api.services.PortfolioService.PortfolioPart;

import java.util.Objects;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.PortfolioService;
import de.axp.framework.example.vaadin.FallbackPortfolioDefinition;
import de.axp.framework.example.vaadin.internal.pages.portfolio.content.PortfolioContentManager;
import de.axp.framework.example.vaadin.internal.pages.portfolio.navigation.PortfolioNavigationManager;
import de.axp.framework.example.vaadin.internal.pages.shared.QueryParametersUtil;

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
		setPortfolioDefinitionToState(QueryParametersUtil.extractPortfolioId(queryParameters));
		setPortfolioPartIndexToState(QueryParametersUtil.extractPartId(queryParameters));
	}

	private void setPortfolioDefinitionToState(String portfolioId) {
		PortfolioDefinition oldPortfolioDefinition = state.getPortfolioDefinition();
		if (Objects.equals(oldPortfolioDefinition.getPortfolioId(), portfolioId)) {
			return;
		}

		PortfolioService portfolioService = framework.getServiceByType(PortfolioService.class);
		PortfolioDefinition newPortfolioDefinition = portfolioService.getPortfolioDefinitions().stream() //
				.filter(d -> d.getPortfolioId().equals(portfolioId)) //
				.findFirst() //
				.orElse(fallbackPortfolioDefinition);
		state.setPortfolioDefinition(newPortfolioDefinition);
	}

	private void setPortfolioPartIndexToState(String partId) {
		PortfolioDefinition portfolioDefinition = state.getPortfolioDefinition();
		PortfolioPart oldPortfolioPart = portfolioDefinition.getPortfolioParts().get(state.getPortfolioPartIndex());
		if (Objects.equals(oldPortfolioPart.getPartId(), partId)) {
			return;
		}

		for (int i = 0; i < portfolioDefinition.getPortfolioParts().size(); i++) {
			PortfolioPart part = portfolioDefinition.getPortfolioParts().get(i);
			if (partId.equals(part.getPartId())) {
				state.setPortfolioPartIndex(i);
				break;
			}
		}
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		navigationManager.update();
		contentManager.update();
	}
}
