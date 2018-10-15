package de.axp.portfolio.vaadin.internal.pages.portfolio.content.simple;

import com.vaadin.flow.component.dependency.StyleSheet;
import de.axp.portfolio.vaadin.internal.pages.portfolio.content.PortfolioContentComponent;

@StyleSheet("frontend://styles/portfolio-simple-content.css")
public class SimpleContentLayout extends PortfolioContentComponent {

	private static final long serialVersionUID = 1482215989642569717L;

	public SimpleContentLayout() {
		setClassName("simple-content");
	}
}
