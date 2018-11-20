package de.axp.portfolio.example.vaadin.internal.pages.shared.links;

import static de.axp.framework.api.services.PortfolioService.PortfolioDefinition;
import static de.axp.framework.api.services.PortfolioService.PortfolioPart;

import java.util.List;

import com.vaadin.flow.component.html.Image;

import de.axp.portfolio.example.vaadin.internal.pages.portfolio.PortfolioPageState;

public class NextPartLink extends PortfolioRouterLink {

	private final PortfolioPageState state;

	public NextPartLink(PortfolioPageState state) {
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
