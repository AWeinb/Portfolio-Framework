package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandFactory;

public class FrameworkFactory {

	public static FrameworkCommandInterface getFrameworkCommandInterface() {
		return CommandFactory.createInterfaceImpl();
	}
}
