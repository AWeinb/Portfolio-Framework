package de.axp.portfolio.vaadin.internal.services.ui.pages.portfolio;

import static de.axp.framework.api.services.UiService.PortfolioDefinition;
import static de.axp.framework.api.services.UiService.PortfolioPart;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.services.ui.pages.portfolio.content.ContentLayout;
import de.axp.portfolio.vaadin.internal.services.ui.pages.portfolio.nav.PortfolioPageNavigation;

@Route(value = "portfolio")
@StyleSheet("frontend://styles/portfoliopage.css")
public class PortfolioPage extends Div implements HasUrlParameter<String>, AfterNavigationObserver {

	private static final long serialVersionUID = 3217286454817153835L;

	private final UiService uiService;
	private final PortfolioPageState currentState;
	private final PortfolioPageNavigation navigation;
	private final ContentLayout contentLayout = new ContentLayout();

	{
		PortfolioFramework framework = UI.getCurrent().getSession().getAttribute(PortfolioFramework.class);
		uiService = framework.getServiceByType(UiService.class);
		currentState = new PortfolioPageState(uiService);
		navigation = new PortfolioPageNavigation(currentState);

		setClassName("pf-portfoliopage");
		add(navigation);
		add(contentLayout);
	}

	@Override
	public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
		String rootSegment = event.getLocation().getFirstSegment();
		String[] split = parameter.split("/");
		String pageSegment = split.length > 0 ? split[0] : "";
		int partIndex = 0;
		try {
			partIndex = Integer.parseInt(split.length > 1 ? split[1] : "0");
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		currentState.update(rootSegment, pageSegment, partIndex);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		navigation.update();

		String pageSegment = currentState.getPageSegment();
		Optional<PortfolioDefinition> portfolioDefinition = uiService.getPortfolioDefinition(pageSegment);
		PortfolioDefinition currentDefinition = portfolioDefinition.orElse(new FallbackPortfolioDefinition());
		List<Class<? extends PortfolioPart>> portfolioParts = currentDefinition.getPortfolioParts();
		Class<? extends PortfolioPart> aClass = portfolioParts.get(currentState.getPageIndex());

		try {
			contentLayout.removeAll();
			contentLayout.add((Component) aClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
