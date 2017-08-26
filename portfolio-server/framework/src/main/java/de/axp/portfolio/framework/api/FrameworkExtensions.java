package de.axp.portfolio.framework.api;

import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.ui.UiService;

public class FrameworkExtensions {

	private CommandService.CommandHandler commandHandler;
	private UiService.UiChangeHandler uiChangeHandler;

	public CommandService.CommandHandler getCommandHandler() {
		return commandHandler;
	}

	public void setCommandHandler(CommandService.CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	public UiService.UiChangeHandler getUiChangeHandler() {
		return uiChangeHandler;
	}

	public void setUiChangeHandler(UiService.UiChangeHandler uiChangeHandler) {
		this.uiChangeHandler = uiChangeHandler;
	}

	public boolean isComplete() {
		boolean isComplete = commandHandler != null;
		isComplete &= uiChangeHandler != null;

		return isComplete;
	}
}
