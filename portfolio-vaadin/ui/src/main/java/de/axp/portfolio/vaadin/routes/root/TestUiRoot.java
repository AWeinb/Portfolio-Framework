package de.axp.portfolio.vaadin.routes.root;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import de.axp.framework.api.AuthenticatedPortfolioFramework;
import de.axp.framework.api.serviceinterfaces.TaskServiceInterface;

@Push
@Route("")
public class TestUiRoot extends Div {

	private static int i;
	private static int i2;

	public TestUiRoot() {
		setHeight("100%");
		add(new LayoutWithTestWidgets());

		VaadinSession session = VaadinSession.getCurrent();
		Object attribute = session.getAttribute(AuthenticatedPortfolioFramework.class.getSimpleName());
		AuthenticatedPortfolioFramework authenticatedFramework = (AuthenticatedPortfolioFramework) attribute;
		authenticatedFramework.getFrameworkTaskService()
				.addTaskHandler(
						(task, promise) -> promise.respond(TaskServiceInterface.TaskResolution.RESOLVED, "Bar"));

		Label label = new Label();
		Button button = new Button("Sh*T");
		button.addClickListener(buttonClickEvent -> authenticatedFramework.getFrameworkTaskService()
				.triggerTask("ID", "Something",
						(resolution, result) -> label.setText(resolution + " - " + result + " " + i++)));
		add(button);
		add(label);

		Label label2 = new Label();
		Button button2 = new Button("Sh*T2");
		button2.addClickListener(buttonClickEvent -> authenticatedFramework.getFrameworkTaskService()
				.triggerTask("Doge", "ID2", "Anything",
						(resolution, result) -> label2.setText(resolution + " - " + result + " " + i2++)));
		add(button2);
		add(label2);
	}
}
