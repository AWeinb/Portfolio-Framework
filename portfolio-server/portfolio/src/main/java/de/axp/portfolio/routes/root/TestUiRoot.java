package de.axp.portfolio.routes.root;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;

@Push
@Route("")
public class TestUiRoot extends Div {

	private static int i;

	public TestUiRoot() {
		setHeight("100%");
		add(new LayoutWithTestWidgets());

		VaadinSession session = VaadinSession.getCurrent();
		Object attribute = session.getAttribute(AuthenticatedFramework.class.getSimpleName());
		AuthenticatedFramework authenticatedFramework = (AuthenticatedFramework) attribute;
		authenticatedFramework.getFrameworkTaskService()
				.addTaskHandler((event, answer) -> answer.on(TaskServiceInterface.TaskResolution.RESOLVED, "Bar"));

		Label label = new Label();
		Button button = new Button("Sh*T");
		button.addClickListener(buttonClickEvent -> authenticatedFramework.getFrameworkTaskService()
				.triggerTask("ID", "Something",
						(resolution, result) -> label.setText(resolution + " - " + result + " " + i++)));

		add(button);
		add(label);
	}
}
