package de.axp.portfolio.ui;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import de.axp.portfolio.framework.FrameworkCommandInterface;

@Push
public class PortfolioUI extends UI {

	private FrameworkCommandInterface frameworkCommandInterface;

	@Override
	protected void init(VaadinRequest request) {
		System.err.println("UI init");

		frameworkCommandInterface = (FrameworkCommandInterface) getSession().getAttribute(FrameworkCommandInterface.class
				.getSimpleName());

		VerticalLayout content = new VerticalLayout();
		content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		content.setSizeFull();

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		content.addComponent(horizontalLayout);

		Label label = new Label();
		horizontalLayout.addComponent(label);

		setContent(content);

		frameworkCommandInterface.addResponseListener((response) -> {
			System.err.println("Got response: " + response);
			label.setValue(response);
			push();
		});

		Button killer = new Button("kill");
		killer.addClickListener((event -> getSession().close()));
		horizontalLayout.addComponent(killer);
	}
}
