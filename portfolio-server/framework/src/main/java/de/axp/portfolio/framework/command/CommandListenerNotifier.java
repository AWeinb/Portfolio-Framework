package de.axp.portfolio.framework.command;

import java.util.LinkedList;
import java.util.List;

public class CommandListenerNotifier {

	private final List<CommandListener> commandListeners = new LinkedList<>();

	void addListener(CommandListener commandListener) {
		if (commandListener == null) {
			throw new IllegalArgumentException();
		}
		commandListeners.add(commandListener);
	}

	void notifyListeners(String command) {
		for (CommandListener commandListener : commandListeners) {
			commandListener.onCommand(command);
		}
	}
}
