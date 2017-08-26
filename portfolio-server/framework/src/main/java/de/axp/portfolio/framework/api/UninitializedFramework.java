package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.InternalFrameworkFactory;
import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.ui.UiService;

public interface UninitializedFramework {

	static UninitializedFramework create() {
		return InternalFrameworkFactory.createUninitializedFramework();
	}

	Framework initialize();

	void setCommandHandler(CommandService.CommandHandler commandHandler);

	void setUiChangeHandler(UiService.UiChangeHandler uiChangeHandler);
}
