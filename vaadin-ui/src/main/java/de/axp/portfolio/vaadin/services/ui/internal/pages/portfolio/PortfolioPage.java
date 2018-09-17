package de.axp.portfolio.vaadin.services.ui.internal.pages.portfolio;

import java.util.List;

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
import de.axp.portfolio.vaadin.services.ui.api.PortfolioDefinition;
import de.axp.portfolio.vaadin.services.ui.api.UiService;
import de.axp.portfolio.vaadin.services.ui.internal.pages.portfolio.content.ContentLayout;
import de.axp.portfolio.vaadin.services.ui.internal.pages.portfolio.nav.PortfolioPageNavigation;

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
		PortfolioDefinition currentDefinition = uiService.getPortfolioDefinition(pageSegment);
		List<Class<? extends Component>> portfolioParts = currentDefinition.getPortfolioParts();

		try {
			contentLayout.removeAll();
			contentLayout.add(portfolioParts.get(currentState.getPageIndex()).newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
