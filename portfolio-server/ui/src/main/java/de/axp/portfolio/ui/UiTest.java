package de.axp.portfolio.ui;

import de.axp.portfolio.model.ui.UTextField;
import de.axp.portfolio.model.ui.UVerticalLayout;

public class UiTest {

	public UVerticalLayout buildView(String tEST) {
		UVerticalLayout uVerticalLayout = new UVerticalLayout();
		UTextField uTextField = new UTextField();
		uTextField.setText(tEST);

		uVerticalLayout.setuTextField(uTextField);

		return uVerticalLayout;
	}
}
