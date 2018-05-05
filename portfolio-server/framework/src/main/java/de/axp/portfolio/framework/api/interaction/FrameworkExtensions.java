package de.axp.portfolio.framework.api.interaction;

import de.axp.portfolio.framework.internal.service.command.CommandService;

public class FrameworkExtensions {

	private CommandService.CommandHandler commandHandler;

	public CommandService.CommandHandler getCommandHandler() {
		return commandHandler;
	}

	public void setCommandHandler(CommandService.CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	public boolean isComplete() {
		return commandHandler != null;
	}
}
