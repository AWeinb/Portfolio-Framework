package de.axp.portfolio.vaadin.api.plugins;

import java.util.List;

import com.vaadin.flow.component.Component;

import de.axp.framework.api.FrameworkPlugin;

public interface PortfolioDefinition extends FrameworkPlugin {

	String getPortfolioId();

	List<Class<? extends Component>> getPortfolioParts();

}
