package de.axp.portfolio.vaadin.internal.services.ui.pages.portfolio.content;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Section;

@StyleSheet("frontend://styles/contentlayout.css")
public class ContentLayout extends Section {

	private static final long serialVersionUID = 1482215989642569717L;

	public ContentLayout() {
		setClassName("pf-content");
	}
}
