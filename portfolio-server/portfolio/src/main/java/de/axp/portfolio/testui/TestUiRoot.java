package de.axp.portfolio.testui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import de.axp.portfolio.framework.api.AuthenticatedFramework;
import de.axp.portfolio.framework.api.GlobalFramework;
import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;

@Route("")
public class TestUiRoot extends Div {

	public TestUiRoot() {
		setHeight("100%");
		add(new LayoutWithTestWidgets());

		VaadinSession current = VaadinSession.getCurrent();
		Object attribute = current.getAttribute(GlobalFramework.class.getSimpleName());
		GlobalFramework framework = (GlobalFramework) attribute;
		AuthenticatedFramework authenticatedFramework = framework.authenticate("Doge");
		authenticatedFramework.getFrameworkTaskService()
				.addTaskHandler((event, answer) -> answer.on(TaskServiceInterface.TaskResolution.RESOLVED, "Bar"));

		Button button = new Button("Sh*T");
		button.addClickListener(buttonClickEvent -> authenticatedFramework.getFrameworkTaskService()
				.triggerTask("ID", "Something",
						(resolution, result) -> System.err.println(resolution + " - " + result)));
		add(button);
	}
}
