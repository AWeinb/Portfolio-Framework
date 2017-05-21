package de.axp.portfolio.framework;

public interface CommandManagement {

	void dispatchCommand(String command);

	interface CommandHandler {
		void handleCommand(String command);
	}
}
