package de.axp.portfolio.ui;

import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.axp.portfolio.model.ui.UVerticalLayout;
import de.axp.portfolio.runtime.RuntimeTest;

public class UiTestPresenter {

	private RuntimeTest runtimeTest;

	public void input() {
		runtimeTest = new RuntimeTest();
		runtimeTest.doSomethingWithData();

	}

	public void update() {
		if(runtimeTest.hasDoneSomething()) {
			UVerticalLayout uiModel = runtimeTest.getUiModel();
			present(uiModel);
		}
	}

	public void present(UVerticalLayout uVerticalLayout) {
		// Visitor map to element or such
		VerticalLayout verticalLayout = new VerticalLayout();
		if (uVerticalLayout.getuTextField() != null) {
			TextField textField = new TextField();
			textField.setValue(uVerticalLayout.getuTextField().getText());
			verticalLayout.addComponent(textField);
		}
		UI.getCurrent().setContent(verticalLayout);
	}
}
