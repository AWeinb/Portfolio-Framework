package de.axp.portfolio.vaadin.internal.pages.shared.links;

import com.vaadin.flow.component.html.Image;
import de.axp.portfolio.vaadin.internal.pages.portfolio.PortfolioPageState;

import java.util.List;

import static de.axp.framework.api.services.UiService.PortfolioDefinition;
import static de.axp.framework.api.services.UiService.PortfolioPart;

public class PortfolioForwardSelector extends PortfolioRouterLink {

	private final PortfolioPageState state;

	public PortfolioForwardSelector(PortfolioPageState state) {
		this.state = state;

		Image image = new Image("frontend/images/arrows_right_double.svg", "");
		image.setClassName("icon");
		add(image);
	}

	@Override
	public void update() {
		setEnabled(state.getPortfolioPartIndex() < state.getPortfolioDefinition().getPortfolioParts().size() - 1);

		super.update();
	}

	@Override
	protected PortfolioDefinition getPortfolioDefinitionTarget() {
		return state.getPortfolioDefinition();
	}

	@Override
	protected PortfolioPart getPortfolioPartTarget() {
		List<? extends PortfolioPart> portfolioParts = state.getPortfolioDefinition().getPortfolioParts();
		int portfolioPartIndex = Math.min(state.getPortfolioPartIndex() + 1, portfolioParts.size() - 1);
		return portfolioParts.get(portfolioPartIndex);
	}
}
