package de.axp.portfolio.example.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;

import de.axp.framework.api.services.PortfolioService;

@StyleSheet("frontend://styles/portfolio-preview-generic.css")
public class GenericPortfolioPreview extends Div implements PortfolioService.PortfolioPreview<Component> {

	public GenericPortfolioPreview(String icon, String caption, String description) {
		setClassName("generic-preview");

		Image iconComponent = new Image(icon, "");
		iconComponent.setClassName("icon");
		add(iconComponent);

		Label captionComponent = new Label(caption);
		captionComponent.setClassName("caption");
		add(captionComponent);

		Label descriptionComponent = new Label(description);
		descriptionComponent.setClassName("description");
		add(descriptionComponent);

	}

	@Override
	public Component getUiComponent() {
		return this;
	}
}
