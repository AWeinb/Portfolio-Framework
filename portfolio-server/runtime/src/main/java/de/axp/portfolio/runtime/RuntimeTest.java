package de.axp.portfolio.runtime;

import de.axp.portfolio.model.ui.UVerticalLayout;
import de.axp.portfolio.ui.UiTest;

public class RuntimeTest {

	public void doSomethingWithData() {

	}

	public boolean hasDoneSomething() {
		return true;
	}

	public UVerticalLayout getUiModel() {
		return new UiTest().buildView("tEST");
	}
}
