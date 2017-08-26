package de.axp.portfolio.framework.internal.service.command;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;

public class CommandFactory {

	public static CommandService createCommandService(MainLoop mainLoop, CommandService.CommandHandler commandHandler) {
		CommandServiceImpl commandService = new CommandServiceImpl(commandHandler);
		mainLoop.addPlugin(commandService);
		return commandService;
	}
}
