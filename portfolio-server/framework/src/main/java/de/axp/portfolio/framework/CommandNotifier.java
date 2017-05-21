package de.axp.portfolio.framework;

public interface CommandNotifier {

	void addCommandListener(CommandListener commandListener);

	void notifyListeners(String command);

	interface CommandListener {
		void onCommand(String command);
	}
}
