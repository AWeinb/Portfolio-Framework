package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.internal.MessageHandlerInterface;
import de.axp.portfolio.framework.internal.CommandManagement;

public class CommandFactory {

	public static CommandManagement createCommandManagement(MessageHandlerInterface messageHandlerInterface) {
		CommandBuffer commandBuffer = new CommandBuffer();
		ResponseBuffer responseBuffer = new ResponseBuffer();
		CommandHandlerNotifier commandHandlerNotifier = new CommandHandlerNotifier(messageHandlerInterface,
				responseBuffer);
		ResponseNotifier responseNotifier = new ResponseNotifier();
		return new CommandManagementImpl(commandBuffer, responseBuffer, commandHandlerNotifier, responseNotifier);
	}
}
