package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandNotifier;

import java.util.LinkedList;
import java.util.List;

public class CommandNotifierImpl implements CommandNotifier {

	private final List<CommandListener> commandListeners = new LinkedList<>();

	@Override
	public void addCommandListener(CommandListener commandListener) {
		if (commandListener == null) {
			throw new IllegalArgumentException();
		}
		commandListeners.add(commandListener);
	}

	@Override
	public void notifyListeners(String command) {
		for (CommandListener commandListener : commandListeners) {
			commandListener.onCommand(command);
		}
	}
}
