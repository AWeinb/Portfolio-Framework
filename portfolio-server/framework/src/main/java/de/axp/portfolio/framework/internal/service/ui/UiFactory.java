package de.axp.portfolio.framework.internal.service.ui;

public class UiFactory {

	public static UiService createUiService(UiService.UiChangeHandler uiChangeHandler) {
		return new UiServiceImpl(uiChangeHandler);
	}
}
