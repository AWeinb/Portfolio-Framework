package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.interaction.FrameworkException;
import de.axp.portfolio.framework.api.interaction.FrameworkExtensions;
import de.axp.portfolio.framework.api.UninitializedFramework;
import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.ui.UiService;

class UninitializedFrameworkImpl implements UninitializedFramework {

	private final FrameworkExtensions frameworkExtensions = new FrameworkExtensions();

	@Override
	public Framework initialize() {
		if (!frameworkExtensions.isComplete()) {
			throw new FrameworkExtensionsIncompleteException();
		}
		return new FrameworkImpl(frameworkExtensions);
	}

	@Override
	public void setCommandHandler(CommandService.CommandHandler commandHandler) {
		frameworkExtensions.setCommandHandler(commandHandler);
	}

	@Override
	public void setUiChangeHandler(UiService.UiChangeHandler uiChangeHandler) {
		frameworkExtensions.setUiChangeHandler(uiChangeHandler);
	}

	private class FrameworkExtensionsIncompleteException extends FrameworkException {

	}
}
