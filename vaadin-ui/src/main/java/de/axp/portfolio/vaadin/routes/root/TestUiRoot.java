package de.axp.portfolio.vaadin.routes.root;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.TaskService;

@Push
@Route("")
public class TestUiRoot extends Div {

	private static int i;
	private static int i2;

	public TestUiRoot() {
		setHeight("100%");
		add(new LayoutWithTestWidgets());

		VaadinSession session = VaadinSession.getCurrent();
		Object attribute = session.getAttribute(PortfolioFramework.class.getSimpleName());
		PortfolioFramework authenticatedFramework = (PortfolioFramework) attribute;

		Label label = new Label();
		Button button = new Button("Sh*T");
		TaskService.Task task1 = TaskService.Task.build("ID", "Something");
		button.addClickListener(buttonClickEvent -> authenticatedFramework.getTaskService()
				.triggerTask(task1, response -> session.access(
						() -> label.setText(response.getResolution() + " - " + response.getContent() + " " + i++))));
		add(button);
		add(label);

		Label label2 = new Label();
		Button button2 = new Button("Sh*T2");
		TaskService.Task task2 = TaskService.Task.build("ID2", "Anything");
		button2.addClickListener(buttonClickEvent -> authenticatedFramework.getTaskService()
				.triggerTask(task2, response -> session.access(
						() -> label2.setText(response.getResolution() + " - " + response.getContent() + " " + i2++))));
		add(button2);
		add(label2);
	}
}
