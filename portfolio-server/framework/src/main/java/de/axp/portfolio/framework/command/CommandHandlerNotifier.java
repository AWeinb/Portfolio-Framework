package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandManagement.CommandHandler;

import java.util.LinkedList;
import java.util.List;

class CommandHandlerNotifier {

	private final List<CommandHandler> commandListeners = new LinkedList<>();

	void addCommandHandler(CommandHandler commandListener) {
		if (commandListener == null) {
			throw new IllegalArgumentException();
		}
		commandListeners.add(commandListener);
	}

	void notifyListeners(String command) {
		for (CommandHandler commandListener : commandListeners) {
			commandListener.handleCommand(command);
		}
	}
}
