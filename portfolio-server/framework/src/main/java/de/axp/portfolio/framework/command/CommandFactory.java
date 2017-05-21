package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandManagement;

public class CommandFactory {

	public static CommandManagement createCommandManagement() {
		CommandBuffer commandBuffer = new CommandBuffer();
		CommandHandlerNotifier commandHandlerNotifier = new CommandHandlerNotifier();
		return new CommandManagementImpl(commandBuffer, commandHandlerNotifier);
	}
}
