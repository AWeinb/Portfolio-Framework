package de.axp.portfolio.vaadin.internal.pages.shared;

import com.vaadin.flow.component.html.Image;
import de.axp.framework.api.services.UiService;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

public class PortfolioBackSelector extends PortfolioRouterLink {

	private final PortfolioPageState state;

	public PortfolioBackSelector(PortfolioPageState state) {
		this.state = state;

		Image image = new Image("frontend/images/arrows_left_double.svg", "");
		image.setClassName("icon");
		add(image);
	}

	@Override
	public void update() {
		setEnabled(state.getPortfolioPartIndex() > 0);

		super.update();
	}

	@Override
	protected UiService.PortfolioDefinition getPortfolioDefinitionTarget() {
		return state.getPortfolioDefinition();
	}

	@Override
	protected UiService.PortfolioPart getPortfolioPartTarget() {
		int portfolioPartIndex = Math.max(0, state.getPortfolioPartIndex() - 1);
		return state.getPortfolioDefinition().getPortfolioParts().get(portfolioPartIndex);
	}
}
